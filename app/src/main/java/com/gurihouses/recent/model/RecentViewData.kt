package com.gurihouses.recent.model

import com.google.gson.annotations.SerializedName

data class RecentViewData(
    @SerializedName("id"            ) var id           : Int?    = null,
    @SerializedName("owner_id"      ) var ownerId      : String? = null,
    @SerializedName("title"         ) var title        : String? = null,
    @SerializedName("location"      ) var location     : String? = null,
    @SerializedName("price"         ) var price        : String? = null,
    @SerializedName("des"           ) var des          : String? = null,
    @SerializedName("listing_type"  ) var listingType  : String? = null,
    @SerializedName("property_type" ) var propertyType : String? = null,
    @SerializedName("image"         ) var image        : String? = null,
    @SerializedName("rooms"         ) var rooms        : String? = null,
    @SerializedName("created_at"    ) var createdAt    : String? = null,
    @SerializedName("code"          ) var code         : String? = null,
    @SerializedName("status"        ) var status       : String? = null,
    @SerializedName("state"         ) var state        : String? = null,
    @SerializedName("city"          ) var city         : String? = null
) {
}