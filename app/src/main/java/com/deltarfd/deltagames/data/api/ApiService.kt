package com.deltarfd.deltagames.data.api

import com.deltarfd.deltagames.data.remote.GameDetailResponse
import com.deltarfd.deltagames.data.remote.GameListResponse
import com.deltarfd.deltagames.data.remote.GameScreenshotResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("games")
    suspend fun getGames(
        @Query("page") page: Int,
        @Query("search") search: String,
        @Query("ordering") ordering: String,
        @Query("dates") dates: String
    ): GameListResponse

    @GET("games/{id}")
    suspend fun getGameDetailById(@Path("id") id: Int): Response<GameDetailResponse>

    @GET("games/{id}/screenshots")
    suspend fun getGameScreenshots(@Path("id") id: Int): Response<GameScreenshotResponse>
}