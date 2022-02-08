package com.gurihouses.otp.ui.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
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
import java.lang.Exception


class OtpActivity : AppCompatActivity() {

    lateinit var binding:ActivityOtpBinding
    private val mViewModel: VerifyOtpViewModel by viewModels()
    private lateinit var sessionManager: SessionManager
    lateinit var number: String
    var user_type = ""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOtpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        user_type = intent.getStringExtra(SessionVar.USER_TYPE).toString()
        number = intent.getStringExtra(SessionVar.KEY_MOBILE_NUM).toString()

        initialization()
        listener()

        getViewModel()
    }

    private fun initialization() {
        binding.number.text = number
        countdown()
       sessionManager = SessionManager(this)

        binding.btnSubmit.setOnClickListener {

            getSignUpApi()

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

                    try {
                        val mrole = response.data?.role
                        val id = response.data?.id
                        sessionManager.createRoleSession(mrole.toString())
                        sessionManager.setIdSession(id.toString())
                        val intent = Intent(this, MainActivity::class.java)
                        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
                        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK
                        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                        intent.putExtra(SessionVar.USER_TYPE, mrole)
                        startActivity(intent)
                        overridePendingTransition(R.anim.fadein, R.anim.fadeout)
                        finish()
                        CommonUtil.showMessage(this, response.message)
                    }catch (e:Exception){
                        e.printStackTrace()
                    }

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


    private fun countdown(){
        object : CountDownTimer(30000, 1000) {
            override fun onTick(millisUntilFinished: Long) {

                //binding.otpTime.text = """Resend code:(${(millisUntilFinished / 1000)})"""

                var seconds = (millisUntilFinished / 1000).toInt()
                val minutes = seconds / 60
                seconds %= 60
                """Resend code:${String.format("%02d", minutes)}:${
                    String.format(
                        "%02d",
                        seconds
                    )
                }""".also { binding.otpTime.text = it }


            }
            override fun onFinish() {
                // do something after countdown is done ie. enable button, change color
                binding.otpTime.visibility = View.GONE
            }
        }.start()
    }

}