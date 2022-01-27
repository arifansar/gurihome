package com.gurihouses.getotp.ui.activities.model

import com.google.gson.annotations.SerializedName

data class LoginResponse(
    @SerializedName("status"  ) var status  : Boolean,
    @SerializedName("message" ) var message : String,
    @SerializedName("otp" ) var otp : String)
