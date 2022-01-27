package com.gurihouses.home.tabfragment.bean

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class StateResponse(

    @SerializedName("status")
    @Expose
    var status: Boolean,
    @SerializedName("message")
    @Expose
    var message: String,
    @SerializedName("data")
    @Expose
    var data: List<Result>

)

data class Result(

    @SerializedName("id")
    @Expose
    var id: Int,
    @SerializedName("name")
    @Expose
    var name: String,
    @SerializedName("status")
    @Expose
    var status: String,
    @SerializedName("updated_at")
    @Expose
    var updatedAt: String
)
