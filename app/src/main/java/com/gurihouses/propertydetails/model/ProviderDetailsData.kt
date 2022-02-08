package com.gurihouses.propertydetails.model

import com.google.gson.annotations.SerializedName

data class ProviderDetailsData(
    @SerializedName("sql"    ) var sql    : DetailsData?              = DetailsData(),
    @SerializedName("images" ) var images : ArrayList<String> = arrayListOf(),
    @SerializedName("owner"  ) var owner  : OwnerDetails?            = OwnerDetails()
)
