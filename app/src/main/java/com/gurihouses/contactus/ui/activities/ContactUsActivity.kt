package com.gurihouses.contactus.ui.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.gurihouses.R
import com.gurihouses.contactus.ui.activities.viewmodel.ContactUsViewModel
import com.gurihouses.databinding.ActivityContactUsBinding
import com.gurihouses.otp.ui.activities.OtpActivity
import com.gurihouses.ui.MainActivity
import com.gurihouses.util.CommonUtil
import com.gurihouses.util.Validation

class ContactUsActivity : AppCompatActivity(), View.OnClickListener {

    lateinit var binding: ActivityContactUsBinding
    private val cotactUsViewModel: ContactUsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityContactUsBinding.inflate(layoutInflater)
        val view= binding.root
        setContentView(view)
        getViewModel()
        initialization()
        listeners()
    }

    private fun initialization() {

    }

    private fun listeners() {
    binding.btnSubmit.setOnClickListener(this)
    }


    private fun checkValidation(){

        var isError = false

        if (!Validation.isStringValue(binding.name.text.toString())) {
            isError = true
            binding.name.requestFocus()
            CommonUtil.showMessage(this, "Enter Name")
            CommonUtil.hideKeyboard(this, binding.name)

        }

        else if (!Validation.isStringValue(binding.email.text.toString())) {

            isError = true
            binding.email.requestFocus()
            CommonUtil.showMessage(this, "Enter Email")
            CommonUtil.hideKeyboard(this, binding.email)

        }

        else if (!Validation.isStringValue(binding.number.text.toString())) {

            isError = true
            binding.number.requestFocus()
            CommonUtil.showMessage(this, "Enter Number")
            CommonUtil.hideKeyboard(this, binding.number)

        }

        else if (!Validation.isStringValue(binding.message.text.toString())) {
            isError = true
            binding.message.requestFocus()
            CommonUtil.showMessage(this, "Enter Message")
            CommonUtil.hideKeyboard(this, binding.message)

        }

        if (!isError) {

            if (Validation.isConnectingToInternet(this)) {

                getContactUsApi()

            } else
                CommonUtil.showMessage(this, "No internet connection!")
        }


    }



    fun getViewModel(){

        cotactUsViewModel.contactUsResponse?.observe(this,{
            if (it!= null){
                val response = it
                val statusCode = response.status
                val message = response.message
                if (statusCode) {

                    val intent = Intent(this, MainActivity::class.java)
                    //val intent = Intent(this, MyProfileActivity::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
                    intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK
                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                    startActivity(intent)
                    overridePendingTransition(R.anim.fadein, R.anim.fadeout)
                    finish()
                    CommonUtil.showMessage(this, response.message)

                }
            }
        })

        cotactUsViewModel.errorMsg?.observe(this, Observer {
            if (it != null) {

                CommonUtil.showMessage(this, it.toString())

            }

        })

        cotactUsViewModel.loadingStatus?.observe(this, Observer {
            if (it == true) {
                binding.progressBar.visibility = View.VISIBLE
            } else {
                binding.progressBar.visibility = View.GONE
            }

        })

    }

    private fun getContactUsApi(){

        cotactUsViewModel.contactUs(binding.name.text.toString(),
        binding.email.text.toString(),binding.number.text.toString(),binding.message.text.toString(),
        "e10adc3949ba59abbe56e057f20f883e")
    }

    override fun onClick(v: View?) {
        when (v?.id) {

            R.id.btn_submit -> {

                checkValidation()
            }

        }
    }


}