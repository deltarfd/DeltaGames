package com.deltarfd.deltagames.data.remote

import com.deltarfd.deltagames.data.remote.PlatformResponse
import com.google.gson.annotations.SerializedName

class MetacriticPlatformResponse(
    @SerializedName("metascore")
    val metascore: Int? = null,
    @SerializedName("platform")
    val platform: PlatformResponse? = null,
    @SerializedName("url")
    val url: String? = null
)