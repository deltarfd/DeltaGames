package com.deltarfd.deltagames.data.repository

import com.deltarfd.deltagames.data.api.ApiService
import javax.inject.Inject

class GameDetailRepository @Inject constructor(private val service: ApiService) {

    suspend fun fetchGameDetail(id: Int) = service.getGameDetailById(id)

    suspend fun fetchGameScreenshots(id: Int) = service.getGameScreenshots(id)
}