package com.gurihouses.profile.models

import com.google.gson.annotations.SerializedName

data class ProfileData(
    @SerializedName("id") var id : Int,
    @SerializedName("role") var role : String,
    @SerializedName("name") var name : String,
    @SerializedName("email") var email : String,
    @SerializedName("mobile" ) var mobile  : String,
    @SerializedName("isd_code") var isdCode : String,
    @SerializedName("address") var address   : String,
    @SerializedName("gender") var gender          : String,
    @SerializedName("otp") var otp             : String,
    @SerializedName("created_at") var createdAt       : String,
    @SerializedName("updated_at") var updatedAt       : String,
    @SerializedName("last_login") var lastLogin       : String,
    @SerializedName("status") var status          : String,
    @SerializedName("fname") var fname           : String,
    @SerializedName("lname") var lname           : String,
    @SerializedName("approved") var approved        : String,
    @SerializedName("city") var city  : String,
    @SerializedName("state") var state           : String,
    @SerializedName("ip") var ip              : String,
    @SerializedName("email_verified_at" ) var emailVerifiedAt : String,
    @SerializedName("profile_image") var profileImage    : String,
    @SerializedName("device_token") var deviceToken     : String

)
