package com.gurihouses.otp.ui.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.widget.addTextChangedListener
import com.gurihouses.R
import com.gurihouses.databinding.ActivityOtpBinding
import android.widget.Toast

import com.gurihouses.ui.MainActivity

import android.text.Editable

import android.text.TextWatcher
import android.view.View
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.gurihouses.about.ui.activities.AboutUsActivity
import com.gurihouses.otp.ui.activities.viewmodel.VerifyOtpViewModel
import com.gurihouses.signup.ui.activities.SignUpActivity
import com.gurihouses.util.CommonUtil
import com.gurihouses.utilities.session.SessionManager
import com.gurihouses.utilities.session.SessionVar


class OtpActivity : AppCompatActivity() {

    lateinit var binding:ActivityOtpBinding
    private val mViewModel: VerifyOtpViewModel by viewModels()
    private lateinit var sessionManager: SessionManager
    lateinit var number: String


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOtpBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initialization()
        listener()

    }

    private fun initialization() {
        sessionManager = SessionManager(this)
        number = sessionManager.getNumber()[SessionVar.KEY_MOBILE_NUM].toString()
        binding.btnSubmit.setOnClickListener {

           val mScreen = Intent(this@OtpActivity, MainActivity::class.java)
            Intent.FLAG_ACTIVITY_NEW_TASK
            Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(mScreen)
            overridePendingTransition(R.anim.fadein, R.anim.fadeout)
            finish()
            //getViewModel()

        }

    }

    private fun listener() {

        binding.txtPinEntry.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable) {
                if (s.toString() == "1234") {

                    Toast.makeText(this@OtpActivity, "correct", Toast.LENGTH_SHORT).show()

                }

                else if (s.length == "1234".length) {
                    Toast.makeText(this@OtpActivity, "Incorrect", Toast.LENGTH_SHORT).show()

                }
            }
        })

    }

    private fun getViewModel(){
        mViewModel.otpResponse?.observe(this) {

            if (it != null) {

                val response = it
                val statusCode = response.status
                val message = response.message
                if (statusCode) {

                    val intent = Intent(this, MainActivity::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
                    intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK
                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                    startActivity(intent)
                    overridePendingTransition(R.anim.fadein, R.anim.fadeout)
                    finish()
                    CommonUtil.showMessage(this, response.message)

                }

            }

        }

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

    private fun getSignUpApi() {

        mViewModel.verifyOtp(number,binding.txtPinEntry.text.toString())

    }


}