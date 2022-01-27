package com.gurihouses.home.tabfragment.bean

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class HomeTabResponse(

    @SerializedName("status")
    @Expose
    var status: Boolean,
    @SerializedName("message")
    @Expose
    var message: String,
    @SerializedName("data")
    @Expose
    var data: List<Datum>

)

data class Datum(

    @SerializedName("id")
    @Expose
     var id: Int,

    @SerializedName("name")
    @Expose
    var name: String
)
