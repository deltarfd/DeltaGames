package com.deltarfd.deltagames.data.remote

import com.deltarfd.deltagames.data.remote.PlatformResponse
import com.google.gson.annotations.SerializedName

class ParentPlatformResponse(
    @SerializedName("platform")
    val platform: PlatformResponse? = null
)