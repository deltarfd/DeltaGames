package com.deltarfd.deltagames.domain.usecase

import com.deltarfd.deltagames.data.remote.GameListItemResponse
import com.deltarfd.deltagames.utils.isNull
import com.deltarfd.deltagames.utils.isNullOrZero
import javax.inject.Inject

class GameListItemValidationUseCase @Inject constructor(
    private val platformValidationUseCase: PlatformValidationUseCase
) {
    operator fun invoke(item: GameListItemResponse): Boolean {
        item.apply {
            return !backgroundImage.isNullOrBlank() &&
                    !metacritic.isNullOrZero() &&
                    !rating.isNull() &&
                    !released.isNullOrBlank() &&
                    !genres.isNullOrEmpty() &&
                    !parentPlatforms.orEmpty()
                        .mapNotNull { it.platform }
                        .none { platformValidationUseCase(it) }
        }
    }
}