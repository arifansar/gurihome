package com.gurihouses.propertydetails.model

import com.google.gson.annotations.SerializedName

data class ProviderDetailsResponse(
    @SerializedName("status"  ) var status  : Boolean? = null,
    @SerializedName("message" ) var message : String?  = null,
    @SerializedName("data"    ) var data    : ProviderDetailsData?    = ProviderDetailsData()
)
