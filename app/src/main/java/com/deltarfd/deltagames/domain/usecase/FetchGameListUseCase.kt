package com.deltarfd.deltagames.domain.usecase

import androidx.paging.PagingData
import androidx.paging.filter
import androidx.paging.map
import com.deltarfd.deltagames.data.repository.GameListRepository
import com.deltarfd.deltagames.domain.mapper.toDomain
import com.deltarfd.deltagames.domain.model.GameListItem
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class FetchGameListUseCase @Inject constructor(
    private val repository: GameListRepository,
    private val gameListItemValidationUseCase: GameListItemValidationUseCase
) {

    operator fun invoke(
        search: String,
        ordering: String,
        dates: String
    ): Flow<PagingData<GameListItem>> {
        return repository.fetchGames(search, ordering, dates).map { pagingData ->
            pagingData.filter {
                gameListItemValidationUseCase(it)
            }.map {
                it.toDomain()
            }
        }
    }
}