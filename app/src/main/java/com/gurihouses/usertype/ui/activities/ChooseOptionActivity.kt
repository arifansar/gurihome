package com.gurihouses.usertype.ui.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.gurihouses.R
import com.gurihouses.getotp.ui.activities.EnterNumberActivity
import com.gurihouses.databinding.ActivityChooseOptionBinding

class ChooseOptionActivity : AppCompatActivity(), View.OnClickListener {

    lateinit var binding: ActivityChooseOptionBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChooseOptionBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        initialization()
        listeners()
    }

    private fun listeners() {
        binding.btnUser.setOnClickListener(this)
    }

    private fun initialization() {

    }

    override fun onClick(v: View?) {

  when (v?.id){

  R.id.btn_user ->{

      val intent = Intent(this, EnterNumberActivity::class.java)
      intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
      intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK
      intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
      startActivity(intent)
      finish()
  }
  }


    }
}