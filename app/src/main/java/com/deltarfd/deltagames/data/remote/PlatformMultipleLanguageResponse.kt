package com.deltarfd.deltagames.data.remote

import com.deltarfd.deltagames.data.remote.PlatformWithImageResponse
import com.google.gson.annotations.SerializedName

class PlatformMultipleLanguageResponse(
    @SerializedName("platform")
    val platform: PlatformWithImageResponse? = null,
    @SerializedName("released_at")
    val releasedAt: String? = null,
    @SerializedName("requirements_en")
    val requirementsEn: Any? = null,
    @SerializedName("requirements_ru")
    val requirementsRu: Any? = null
)