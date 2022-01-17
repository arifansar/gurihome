package com.gurihouses.util

import android.app.Activity
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.text.TextUtils
import android.util.Patterns
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import com.gurihouses.base.GuriBaseApp
import java.util.regex.Pattern

object Validation {

    val emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"
    val REGEX_MOBILE   = "^(?:(?:\\+|0{0,2})91(\\s*[\\ -]\\s*)?|[0]?)?[6789]\\d{9}|(\\d[ -]?){10}\\d\$"
    val passwordpattern = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{6,}$"


    fun isTextValue(editText: TextView): Boolean {
        val getValue = editText.text.toString().trim { it <= ' ' }
        return getValue != ""
    }


    fun isStringValue(value: String?): Boolean {
        return !(value == null || value == "" || value == " ")
    }

    fun isValidEmail(target: CharSequence?): Boolean {
        return !TextUtils.isEmpty(target) && target!!.matches(emailPattern.toRegex())

    }

    fun isValidPassword(password: String): Boolean {

        val pattern = Pattern.compile(passwordpattern)
        val matcher = pattern.matcher(password)

        return matcher.matches()
    }

    fun isConnectingToInternet(context: Context?): Boolean {
        val connectivity = GuriBaseApp.instance
            .getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (connectivity != null) {
            val info = connectivity.allNetworkInfo
            if (info != null) for (i in info.indices) if (info[i]
                    .state == NetworkInfo.State.CONNECTED
            ) {
                return true
            }
        }
        return false
    }



    fun isValidMobile(phone: String): Boolean {
        return !TextUtils.isEmpty(phone) &&  phone.matches(REGEX_MOBILE.toRegex())
    }


    fun checkValidation(target: String): Boolean {

        return if (target.contains("@")) {
            Patterns.EMAIL_ADDRESS.matcher(target).matches()
        } else {
            //Patterns.PHONE.matcher(target).matches()
            target.matches(REGEX_MOBILE.toRegex())
        }
    }



    fun hideKeyboard(activity: Activity) {
        val imm = activity.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        var view = activity.currentFocus
        if (view == null) {
            view = View(activity)
        }
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }



}