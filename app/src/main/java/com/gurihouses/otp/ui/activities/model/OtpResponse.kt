package com.gurihouses.otp.ui.activities.model

import com.google.gson.annotations.SerializedName

data class OtpResponse (
    @SerializedName("status"  ) var status  : Boolean,
    @SerializedName("message" ) var message : String,
    @SerializedName("data"    ) var data    : OtpResponseData?  = OtpResponseData()
        )