package com.gurihouses.getotp.ui.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.gurihouses.R
import com.gurihouses.databinding.ActivityEnterNumberBinding
import com.gurihouses.signup.ui.activities.SignUpActivity

class EnterNumberActivity : AppCompatActivity(), View.OnClickListener {

    lateinit var binding: ActivityEnterNumberBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEnterNumberBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        initialization()
        listeners()
    }

    private fun listeners() {
        binding.signUp.setOnClickListener(this)
    }

    private fun initialization() {

    }

    override fun onClick(v: View?) {

        when (v?.id){

           R.id.sign_up ->{
               val intent = Intent(this, SignUpActivity::class.java)
               intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
               intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
               intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
               startActivity(intent)
               finish()
           }

        }
    }
}