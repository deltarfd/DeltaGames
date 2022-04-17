package com.deltarfd.deltagames.data.remote

import com.deltarfd.deltagames.data.remote.YearDetailResponse
import com.google.gson.annotations.SerializedName

class FiltersResponse(
    @SerializedName("years")
    val yearDetails: List<YearDetailResponse>? = null
)