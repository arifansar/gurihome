package com.gurihouses.chat.bottomchat.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.gurihouses.R
import com.gurihouses.databinding.ActivityChatBinding

class ChatActivity : AppCompatActivity() {

    lateinit var binding:ActivityChatBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityChatBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        initialization()
        listener()

    }

    private fun initialization() {

        binding.chatInToolbar.ttitle.text = "Chat with David"


    }

    private fun listener() {
        binding.chatInToolbar.backIcon.setOnClickListener {
            onBackPressed()
            overridePendingTransition(R.anim.fadein, R.anim.fadeout)
        }

    }

    override fun onBackPressed() {
        super.onBackPressed()

    }
}