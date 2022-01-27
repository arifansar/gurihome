package com.gurihouses.otp.ui.activities.model

import com.google.gson.annotations.SerializedName

data class OtpResponseData(
    @SerializedName("id") var id : Int?    = null,
    @SerializedName("role") var role : String? = null,
    @SerializedName("name") var name : String? = null,
    @SerializedName("email") var email : String? = null,
    @SerializedName("mobile") var mobile : String? = null,
    @SerializedName("isd_code") var isdCode : String? = null,
    @SerializedName("address") var address : String? = null,
    @SerializedName("gender") var gender : String? = null,
    @SerializedName("otp") var otp : String? = null,
    @SerializedName("created_at") var createdAt : String? = null,
    @SerializedName("updated_at") var updatedAt : String? = null,
    @SerializedName("last_login") var lastLogin : String? = null,
    @SerializedName("status") var status : String? = null,
    @SerializedName("fname") var fname : String? = null,
    @SerializedName("lname") var lname : String? = null,
    @SerializedName("approved") var approved : String? = null,
    @SerializedName("city") var city : String? = null,
    @SerializedName("state") var state : String? = null,
    @SerializedName("ip") var ip : String? = null,
    @SerializedName("email_verified_at" ) var emailVerifiedAt : String? = null,
    @SerializedName("profile_image") var profileImage : String? = null,
    @SerializedName("device_token") var deviceToken : String? = null
)
