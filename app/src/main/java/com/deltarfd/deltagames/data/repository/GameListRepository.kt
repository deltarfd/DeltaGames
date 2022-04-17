package com.deltarfd.deltagames.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.deltarfd.deltagames.data.api.ApiService
import com.deltarfd.deltagames.data.source.GamesPagingSource
import javax.inject.Inject

class GameListRepository @Inject constructor(private val service: ApiService) {
    fun fetchGames(
        search: String,
        ordering: String,
        dates: String
    ) = Pager(
        PagingConfig(
            pageSize = 20,
            enablePlaceholders = true
        )
    ) {
        GamesPagingSource(
            service,
            search,
            ordering,
            dates
        )
    }.flow
}