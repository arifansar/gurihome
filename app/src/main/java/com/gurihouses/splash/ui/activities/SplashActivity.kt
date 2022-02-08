package com.gurihouses.splash.ui.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.gurihouses.R
import com.gurihouses.ui.MainActivity
import com.gurihouses.usertype.ui.activities.ChooseOptionActivity
import com.gurihouses.utilities.session.SessionManager
import com.gurihouses.utilities.session.SessionVar
import com.gurihouses.utilities.session.SessionVar.USER_TYPE
import java.lang.Exception

class SplashActivity : AppCompatActivity() {

    private lateinit var sessionManager: SessionManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        sessionManager = SessionManager(this)

        Handler(Looper.getMainLooper()).postDelayed({

            try {
                val id = sessionManager.getIdSession()[SessionVar.ID].toString()
                if (id.isNotEmpty()) {
                    intent = Intent(this, MainActivity::class.java)
                    intent.putExtra(USER_TYPE,"owner")
                    startActivity(intent)
                    overridePendingTransition(R.anim.fadein, R.anim.fadeout)
                    finish()

                } else {

                    intent = Intent(this, ChooseOptionActivity::class.java)
                    startActivity(intent)
                    overridePendingTransition(R.anim.fadein, R.anim.fadeout)
                    finish()


                }
            }catch (e:Exception){
                e.printStackTrace()
            }

        }, 3000)
    }
}