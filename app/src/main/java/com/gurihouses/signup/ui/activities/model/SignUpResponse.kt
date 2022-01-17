package com.gurihouses.signup.ui.activities.model

import com.google.gson.annotations.SerializedName

data class SignUpResponse(

    @SerializedName("status" )
    var status  : Boolean,
    @SerializedName("message")
    var message : String,
    @SerializedName("otp" )
    var otp : Int,
    @SerializedName("data")
    var data : ArrayList<String> = arrayListOf()

)
