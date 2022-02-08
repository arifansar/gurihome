package com.gurihouses.signup.ui.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.gurihouses.R
import com.gurihouses.databinding.ActivitySignUpBinding
import com.gurihouses.getotp.ui.activities.EnterNumberActivity
import com.gurihouses.otp.ui.activities.OtpActivity
import com.gurihouses.signup.ui.activities.viewmodel.SignUpViewModel
import com.gurihouses.ui.MainActivity
import com.gurihouses.util.CommonUtil
import com.gurihouses.util.Validation
import com.gurihouses.utilities.session.SessionVar

class SignUpActivity : AppCompatActivity(), View.OnClickListener {

    lateinit var binding: ActivitySignUpBinding
    private val mViewModel: SignUpViewModel by viewModels()
    var user_type = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        initialized()
        listener()
        getViewModel()

    }

    private fun initialized() {
        user_type = intent.getStringExtra(SessionVar.USER_TYPE).toString()
        user_type.let {
            user_type = it
        }



    }

    private fun listener() {

        binding.btnNext.setOnClickListener(this)
        binding.signIn.setOnClickListener(this)
    }

    private fun getViewModel() {

        mViewModel.signUpResponse?.observe(this, Observer {
            if (it != null) {

                val response = it
                val statusCode = response.status
                val message = response.message
                if (statusCode) {

                    val intent = Intent(this, OtpActivity::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
                    intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK
                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                    intent.putExtra(SessionVar.KEY_MOBILE_NUM,binding.editMobile.text.toString())
                    startActivity(intent)
                    overridePendingTransition(R.anim.fadein, R.anim.fadeout)
                    finish()
                    CommonUtil.showMessage(this, response.message)
                }

            }

        })

        mViewModel.errorMsg?.observe(this, Observer {
            if (it != null) {

                CommonUtil.showMessage(this, it.toString())

            }

        })

        mViewModel.loadingStatus?.observe(this, Observer {
            if (it == true) {
                binding.progressBar.visibility = View.VISIBLE
            } else {
                binding.progressBar.visibility = View.GONE
            }

        })
    }

    override fun onClick(v: View?) {

        when (v?.id) {

            R.id.btn_next -> {

                checkValidation()
            }

            R.id.sign_in->{

                val intent = Intent(this, EnterNumberActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                intent.putExtra(SessionVar.USER_TYPE,user_type)
                startActivity(intent)
                overridePendingTransition(R.anim.fadein, R.anim.fadeout)
                finish()

            }

        }

    }

    private fun checkValidation() {
        var isError = false

        if (!Validation.isStringValue(binding.editFirstName.text.toString())) {
            isError = true
            binding.editFirstName.requestFocus()
            CommonUtil.showMessage(this, "Enter first name")
            CommonUtil.hideKeyboard(this, binding.editFirstName)

        } else if (!Validation.isStringValue(binding.editLastName.text.toString())) {

            isError = true
            binding.editLastName.requestFocus()
            CommonUtil.showMessage(this, "Enter last name")
            CommonUtil.hideKeyboard(this, binding.editLastName)

        }
        else if (!Validation.isStringValue(binding.editMobile.text.toString())) {

            isError = true
            binding.editMobile.requestFocus()
            CommonUtil.showMessage(this, "Enter Mobile Number")
            CommonUtil.hideKeyboard(this, binding.editMobile)


        }
        else if (!Validation.isStringValue(binding.editEmail.text.toString())) {

            isError = true
            binding.editEmail.requestFocus()
            CommonUtil.showMessage(this, "Enter Email")
            CommonUtil.hideKeyboard(this, binding.editEmail)

        }

        if (!isError) {

            if (Validation.isConnectingToInternet(this)) {

                getSignUpApi()

            } else
                CommonUtil.showMessage(this, "No internet connection!")
        }


    }

    private fun getSignUpApi() {

        mViewModel.getSignUpDetails(
            user_type,
            binding.editFirstName.text.toString().trim(),
            binding.editLastName.text.toString().trim(),
            binding.editMobile.text.toString().trim(),
            "temp",
            binding.editEmail.text.toString().trim()//fill from edittext
        )
    }

}