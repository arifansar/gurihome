package com.gurihouses.recent.model

import com.google.gson.annotations.SerializedName

data class RecentViewResponse(
    @SerializedName("status"  ) var status  : Boolean?        = null,
    @SerializedName("message" ) var message : String?         = null,
    @SerializedName("data"    ) var data    : ArrayList<RecentViewData> = arrayListOf()
)
