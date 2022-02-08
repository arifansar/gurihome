package com.gurihouses.contactus.ui.activities.model

import com.google.gson.annotations.SerializedName

data class ContactUsResponse(
    @SerializedName("status"  ) var status  : Boolean,
    @SerializedName("message" ) var message : String
)
