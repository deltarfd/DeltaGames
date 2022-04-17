package com.deltarfd.deltagames.domain.mapper

import com.deltarfd.deltagames.data.remote.GameDetailResponse
import com.deltarfd.deltagames.domain.model.GameDetailInfo
import com.deltarfd.deltagames.utils.convertToDate
import com.deltarfd.deltagames.utils.orIntMin
import com.deltarfd.deltagames.utils.orZero

fun GameDetailResponse.toDomain(): GameDetailInfo {
    return GameDetailInfo(
        id = id.orIntMin(),
        name = name.orEmpty(),
        backgroundImage = backgroundImage.orEmpty(),
        releaseDate = released.orEmpty().convertToDate(),
        publisher = (if (!publishers.isNullOrEmpty()) publishers[0].name else developers.orEmpty()
            .getOrNull(0)?.name).orEmpty(),
        rating = rating.orZero().toFloat(),
        metascore = metacritic.orZero(),
        genres = genres.orEmpty().mapNotNull { it.name }.sortedBy { it.length },
        description = descriptionRaw.orEmpty(),
        platforms = parentPlatforms.orEmpty().mapNotNull { it.platform }.mapNotNull { it.slug }
    )
}