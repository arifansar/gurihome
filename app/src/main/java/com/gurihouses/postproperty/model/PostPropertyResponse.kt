package com.gurihouses.postproperty.model

import com.google.gson.annotations.SerializedName

data class PostPropertyResponse(
    @SerializedName("status"  ) var status  : Boolean,
    @SerializedName("message" ) var message : String,
    @SerializedName("data"    ) var data    : ArrayList<String> = arrayListOf()
)
