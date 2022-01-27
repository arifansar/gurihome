package com.gurihouses.utilities.session

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import com.gurihouses.getotp.ui.activities.EnterNumberActivity
import com.gurihouses.utilities.session.SessionVar.KEY_ACCESS_TOKEN
import com.gurihouses.utilities.session.SessionVar.KEY_EMAIL
import com.gurihouses.utilities.session.SessionVar.KEY_MOBILE_NUM
import java.util.HashMap

object SessionVar {

    const val KEY_ACCESS_TOKEN = "key_access_token"
    const val KEY_EMAIL = "email"
    const val KEY_MOBILE_NUM = "number"

}

private const val PREF_NAME = "guri_houses"

class SessionManager(context: Context) {
    private val appContext = context.applicationContext
    private val prefrence: SharedPreferences = appContext.getSharedPreferences(PREF_NAME, 0)
    private val editor: SharedPreferences.Editor = prefrence.edit()

    fun createTokenSession(token: String) {
        editor.putString(KEY_ACCESS_TOKEN, token).apply()
    }

    fun getAccessToken(): HashMap<String, String> {
        val user = HashMap<String, String>()
        user.put(KEY_ACCESS_TOKEN, prefrence.getString(KEY_ACCESS_TOKEN, null).toString())
        return user
    }

    fun createNumberSession(email: String) {
        editor.putString(KEY_MOBILE_NUM, email).apply()
    }

    fun getNumber(): HashMap<String, String> {
        val user = HashMap<String, String>()
        user.put(KEY_MOBILE_NUM, prefrence.getString(KEY_MOBILE_NUM, null).toString())
        return user
    }

    fun logoutUser() {
        editor.clear()
        editor.apply()
        val intent = Intent(appContext, EnterNumberActivity::class.java)
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        appContext.startActivity(intent)
    }
}