package com.gurihouses.propertydetails.model

import com.google.gson.annotations.SerializedName

data class OwnerDetails(
    @SerializedName("id"            ) var id           : Int?    = null,
    @SerializedName("name"          ) var name         : String? = null,
    @SerializedName("mobile"        ) var mobile       : String? = null,
    @SerializedName("email"         ) var email        : String? = null,
    @SerializedName("profile_image" ) var profileImage : String? = null
)
