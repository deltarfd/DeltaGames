package com.deltarfd.deltagames.data.remote

import com.deltarfd.deltagames.data.remote.YearResponse
import com.google.gson.annotations.SerializedName

class YearDetailResponse(
    @SerializedName("count")
    val count: Int? = null,
    @SerializedName("decade")
    val decade: Int? = null,
    @SerializedName("filter")
    val filter: String? = null,
    @SerializedName("from")
    val from: Int? = null,
    @SerializedName("nofollow")
    val nofollow: Boolean? = null,
    @SerializedName("to")
    val to: Int? = null,
    @SerializedName("years")
    val years: List<YearResponse>? = null
)