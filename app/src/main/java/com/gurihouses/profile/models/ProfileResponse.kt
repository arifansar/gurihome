package com.gurihouses.profile.models

import com.google.gson.annotations.SerializedName

data class ProfileResponse(
    @SerializedName("status" )
    var status  : Boolean,
    @SerializedName("message")
    var message : String,
    @SerializedName("data")
    var data : ProfileData
)
