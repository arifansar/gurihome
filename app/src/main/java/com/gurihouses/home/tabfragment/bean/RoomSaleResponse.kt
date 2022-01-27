package com.gurihouses.home.tabfragment.bean

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class RoomSaleResponse(



    @SerializedName("name")
    @Expose
    var name: String,

    @SerializedName("realname")
    @Expose
    var realname: String,

    @SerializedName("team")
    @Expose
    var team: String,
    @SerializedName("firstappearance")
    @Expose
    var firstappearance: String,
    @SerializedName("createdby")
    @Expose
    var createdby: String,
    @SerializedName("publisher")
    @Expose
    var publisher: String,

    @SerializedName("imageurl")
    @Expose
    var imageurl: Int,

    @SerializedName("bio")
    @Expose
    var bio: String,

    )

