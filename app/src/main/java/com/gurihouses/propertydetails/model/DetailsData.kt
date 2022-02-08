package com.gurihouses.propertydetails.model

import com.google.gson.annotations.SerializedName

data class DetailsData(
    @SerializedName("id"            ) var id           : Int?    = null,
    @SerializedName("owner_id"      ) var ownerId      : String? = null,
    @SerializedName("title"         ) var title        : String? = null,
    @SerializedName("location"      ) var location     : String? = null,
    @SerializedName("lat"           ) var lat          : String? = null,
    @SerializedName("lng"           ) var lng          : String? = null,
    @SerializedName("price"         ) var price        : String? = null,
    @SerializedName("des"           ) var des          : String? = null,
    @SerializedName("image"         ) var image        : String? = null,
    @SerializedName("listing_type"  ) var listingType  : String? = null,
    @SerializedName("property_type" ) var propertyType : String? = null,
    @SerializedName("category"      ) var category     : String? = null,
    @SerializedName("status"        ) var status       : String? = null,
    @SerializedName("approved"      ) var approved     : String? = null,
    @SerializedName("rooms"         ) var rooms        : String? = null,
    @SerializedName("area"          ) var area         : String? = null,
    @SerializedName("balcony"       ) var balcony      : String? = null,
    @SerializedName("face"          ) var face         : String? = null,
    @SerializedName("created_at"    ) var createdAt    : String? = null,
    @SerializedName("updated_at"    ) var updatedAt    : String? = null,
    @SerializedName("approved_by"   ) var approvedBy   : String? = null,
    @SerializedName("state"         ) var state        : String? = null,
    @SerializedName("city"          ) var city         : String? = null,
    @SerializedName("code"          ) var code         : String? = null
)
