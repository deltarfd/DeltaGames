package com.deltarfd.deltagames.data.remote

import com.deltarfd.deltagames.data.remote.ScreenshotResponse
import com.google.gson.annotations.SerializedName

class GameScreenshotResponse(
    @SerializedName("count")
    val count: Int? = null,
    @SerializedName("results")
    val results: List<ScreenshotResponse>? = null
)