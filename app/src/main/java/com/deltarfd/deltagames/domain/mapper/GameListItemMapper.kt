package com.deltarfd.deltagames.domain.mapper

import com.deltarfd.deltagames.data.remote.GameListItemResponse
import com.deltarfd.deltagames.domain.model.GameListItem
import com.deltarfd.deltagames.utils.orIntMin

fun GameListItemResponse.toDomain(): GameListItem {
    return GameListItem(
        id = id.orIntMin(),
        name = name.orEmpty(),
        imageUrl = backgroundImage.orEmpty(),
        platforms = parentPlatforms.orEmpty().mapNotNull { it.platform?.slug }
    )
}