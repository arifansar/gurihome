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
import com.gurihouses.about.ui.activities.AboutUsActivity
import com.gurihouses.signup.ui.activities.SignUpActivity


class OtpActivity : AppCompatActivity() {

    lateinit var binding:ActivityOtpBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityOtpBinding.inflate(layoutInflater)
        setContentView(binding.root)


        initialization()
        listener()


    }

    private fun initialization() {

        binding.btnSubmit.setOnClickListener {

            val mScreen = Intent(this@OtpActivity, SignUpActivity::class.java)
            Intent.FLAG_ACTIVITY_NEW_TASK
            Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(mScreen)
            overridePendingTransition(R.anim.fadein, R.anim.fadeout)
            finish()

        }

    }

    private fun listener() {

        binding.txtPinEntry.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable) {
                if (s.toString() == "1234") {

                    Toast.makeText(this@OtpActivity, "correct", Toast.LENGTH_SHORT).show()

                } else if (s.length == "1234".length) {
                    Toast.makeText(this@OtpActivity, "Incorrect", Toast.LENGTH_SHORT).show()

                }
            }
        })

    }
}