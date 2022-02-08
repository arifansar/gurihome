package com.gurihouses.getotp.ui.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.gurihouses.R
import com.gurihouses.databinding.ActivityEnterNumberBinding
import com.gurihouses.getotp.ui.activities.viewmodel.LoginViewModel
import com.gurihouses.otp.ui.activities.OtpActivity
import com.gurihouses.signup.ui.activities.SignUpActivity
import com.gurihouses.ui.MainActivity
import com.gurihouses.util.CommonUtil
import com.gurihouses.util.Validation
import com.gurihouses.utilities.session.SessionManager
import com.gurihouses.utilities.session.SessionVar

class EnterNumberActivity : AppCompatActivity(), View.OnClickListener {

    lateinit var binding: ActivityEnterNumberBinding
    private val mViewModel : LoginViewModel by viewModels()
    private lateinit var sessionManager: SessionManager
    var user_type = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEnterNumberBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        user_type = intent.getStringExtra(SessionVar.USER_TYPE).toString()

        initialization()
        listeners()
        getViewModel()
    }

    private fun listeners() {
        binding.signUp.setOnClickListener(this)
        binding.btnOtp.setOnClickListener(this)
        binding.skip.setOnClickListener(this)
    }

    private fun initialization() {
        sessionManager = SessionManager(this)


    }

    override fun onClick(v: View?) {

        when (v?.id){

            R.id.btn_otp->{
                checkValidation()

            }

           R.id.sign_up ->{
               val intent = Intent(this, SignUpActivity::class.java)
               intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
               intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK
               intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
               intent.putExtra(SessionVar.USER_TYPE,user_type)
               startActivity(intent)
               overridePendingTransition(R.anim.fadein, R.anim.fadeout)
               finish()
           }

            R.id.skip->{
//
//                val intent = Intent(this, MainActivity::class.java)
//                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
//                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK
//                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
//                intent.putExtra(SessionVar.USER_TYPE,user_type)
//                startActivity(intent)
//                overridePendingTransition(R.anim.fadein, R.anim.fadeout)
//                finish()

            }

        }
    }

    private fun checkValidation(){

        var isError = false

        if (!Validation.isStringValue(binding.editMobile.text.toString())) {
            isError = true
            binding.editMobile.requestFocus()
            CommonUtil.showMessage(this, "Enter Mobile Number")
            CommonUtil.hideKeyboard(this, binding.editMobile)

        }
        if (!isError) {

            if (Validation.isConnectingToInternet(this)) {

                getLoginApi()

            } else
                CommonUtil.showMessage(this, "No internet connection!")
        }


    }



    private fun getViewModel() {

        /* Normal Sign up */
        mViewModel.loginResponse?.observe(this, Observer {
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

    private fun getLoginApi(){
        mViewModel.login(binding.editMobile.text.toString())
//        sessionManager.createNumberSession(binding.editMobile.text.toString())

    }


}