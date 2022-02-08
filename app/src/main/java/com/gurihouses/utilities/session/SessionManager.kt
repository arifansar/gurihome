package com.gurihouses.utilities.session

import android.app.PendingIntent.getActivity
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import com.gurihouses.getotp.ui.activities.EnterNumberActivity
import com.gurihouses.utilities.session.SessionVar.KEY_ACCESS_TOKEN
import com.gurihouses.utilities.session.SessionVar.KEY_EMAIL
import com.gurihouses.utilities.session.SessionVar.KEY_MOBILE_NUM
import com.gurihouses.utilities.session.SessionVar.LATITUDE
import com.gurihouses.utilities.session.SessionVar.LONGITUDE
import com.gurihouses.utilities.session.SessionVar.OWNER_CITY
import com.gurihouses.utilities.session.SessionVar.OWNER_STATE
import com.gurihouses.utilities.session.SessionVar.STATE_ID
import com.gurihouses.utilities.session.SessionVar.USER_TYPE
import java.util.HashMap
import android.provider.MediaStore

import android.database.Cursor

import java.io.File

import android.net.Uri
import com.gurihouses.R
import com.gurihouses.ui.MainActivity
import com.gurihouses.usertype.ui.activities.ChooseOptionActivity
import com.gurihouses.utilities.session.SessionVar.ID


object SessionVar {

    const val KEY_ACCESS_TOKEN = "key_access_token"
    const val KEY_EMAIL = "email"
    const val KEY_MOBILE_NUM = "number"
    const val PROPERTY_OWNER = "owner"
    const val PROPERTY_USER = "user"
    const val USER_TYPE ="user_type"
    const val LATITUDE = "lat"
    const val LONGITUDE = "lng"
    const val STATE_ID = "state_id"
    const val OWNER_CITY = "city"
    const val OWNER_STATE = "state"
    const val ID = "id"

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
        user[KEY_ACCESS_TOKEN] = prefrence.getString(KEY_ACCESS_TOKEN, null).toString()
        return user
    }



    fun createRoleSession(role: String) {
        editor.putString(USER_TYPE, role).apply()
    }

    fun getRole(): HashMap<String, String> {
        val user = HashMap<String, String>()
        user[USER_TYPE] = prefrence.getString(USER_TYPE, null).toString()
        return user
    }


    fun createNumberSession(email: String) {
        editor.putString(KEY_MOBILE_NUM, email).apply()
    }

    fun getNumber(): HashMap<String, String> {
        val user = HashMap<String, String>()
        user[KEY_MOBILE_NUM] = prefrence.getString(KEY_MOBILE_NUM, null).toString()
        return user
    }

    fun setLatitudeSession(lat: String) {
        editor.putString(LATITUDE, lat).apply()
    }

    fun getLatitudeSession(): HashMap<String, String> {
        val user = HashMap<String, String>()
        user[LATITUDE] = prefrence.getString(LATITUDE, null).toString()
        return user
    }


    fun setLongitudeSession(lng: String) {
        editor.putString(LONGITUDE, lng).apply()
    }

    fun getLongitudeSession(): HashMap<String, String> {
        val user = HashMap<String, String>()
        user[LONGITUDE] = prefrence.getString(LONGITUDE, null).toString()
        return user
    }



    fun setStateIdSession(state_id: String) {
        editor.putString(STATE_ID, state_id).apply()
    }

    fun getStateSession(): HashMap<String, String> {
        val user = HashMap<String, String>()
        user[STATE_ID] = prefrence.getString(STATE_ID, null).toString()
        return user
    }



    fun setCitySession(city: String) {
        editor.putString(OWNER_CITY, city).apply()
    }

    fun getCitySession(): HashMap<String, String> {
        val user = HashMap<String, String>()
        user[OWNER_CITY] = prefrence.getString(OWNER_CITY, null).toString()
        return user
    }



    fun setStateSession(state: String) {
        editor.putString(OWNER_STATE, state).apply()
    }

    fun getStatSession(): HashMap<String, String> {
        val user = HashMap<String, String>()
        user[OWNER_STATE] = prefrence.getString(OWNER_STATE, null).toString()
        return user
    }


    fun setIdSession(id: String) {
        editor.putString(ID, id).apply()
    }

    fun getIdSession(): HashMap<String, String> {
        val user = HashMap<String, String>()
        user[ID] = prefrence.getString(ID, null).toString()
        return user
    }



    fun logoutUser() {
        editor.clear()
        editor.apply()
        val intent = Intent(appContext, ChooseOptionActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        appContext.startActivity(intent)
    }


}