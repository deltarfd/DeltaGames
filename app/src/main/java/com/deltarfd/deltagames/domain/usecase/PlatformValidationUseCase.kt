package com.deltarfd.deltagames.domain.usecase

import com.deltarfd.deltagames.domain.model.Platforms
import com.deltarfd.deltagames.data.remote.PlatformResponse
import javax.inject.Inject

class PlatformValidationUseCase @Inject constructor() {
    operator fun invoke(platform: PlatformResponse): Boolean {
        return if (platform.slug.isNullOrBlank()) false
        else {
            platform.slug == Platforms.PC ||
                    platform.slug == Platforms.PLAYSTATION ||
                    platform.slug == Platforms.XBOX ||
                    platform.slug == Platforms.NINTENDO
        }
    }
}