package com.gurihouses.owner.bean

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class OwnerPropertyResponse(

    @SerializedName("status")
    @Expose
    var status: Boolean,
    @SerializedName("message")
    @Expose
    var message: String,
    @SerializedName("data")
    @Expose
    var data: List<OwnerProperty>
)
data class OwnerProperty(

    @SerializedName("id")
    @Expose
    var id: Int,
    @SerializedName("owner_id")
    @Expose
    var owner_id: String,
    @SerializedName("title")
    @Expose
    var title: String,
    @SerializedName("location")
    @Expose
    var location: String,

    @SerializedName("price")
    @Expose
    var price: String,

    @SerializedName("des")
    @Expose
    var des: String,

    @SerializedName("listing_type")
    @Expose
    var listing_type: String,

    @SerializedName("property_type")
    @Expose
    var property_type: String,

    @SerializedName("image")
    @Expose
    var image: String? = null,

    @SerializedName("rooms")
    @Expose
    var rooms: String,

    @SerializedName("created_at")
    @Expose
    var created_at: String,

    @SerializedName("code")
    @Expose
    var code: String,

    @SerializedName("status")
    @Expose
    var status: String,

    @SerializedName("state")
    @Expose
    var state: String,

    @SerializedName("city")
    @Expose
    var city: String,

    )
