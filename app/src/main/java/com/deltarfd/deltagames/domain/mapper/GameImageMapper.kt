package com.deltarfd.deltagames.domain.mapper

import com.deltarfd.deltagames.data.remote.GameDetailResponse
import com.deltarfd.deltagames.data.remote.ScreenshotResponse
import com.deltarfd.deltagames.domain.model.GameImage
import com.deltarfd.deltagames.utils.orIntMin

fun ScreenshotResponse.toDomain(): GameImage {
    return GameImage(
        id = id.orIntMin(),
        imageUrl = image.orEmpty()
    )
}

fun GameDetailResponse.toGameImage(): GameImage {
    return GameImage(id.orIntMin(), backgroundImage.orEmpty())
}