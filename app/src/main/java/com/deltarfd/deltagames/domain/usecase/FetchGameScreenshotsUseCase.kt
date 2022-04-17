package com.deltarfd.deltagames.domain.usecase

import com.deltarfd.deltagames.data.Resource
import com.deltarfd.deltagames.data.repository.GameDetailRepository
import com.deltarfd.deltagames.domain.mapper.toDomain
import com.deltarfd.deltagames.utils.map
import javax.inject.Inject
import kotlinx.coroutines.flow.flow

class FetchGameScreenshotsUseCase @Inject constructor(private val repository: GameDetailRepository) {

    operator fun invoke(id: Int) = flow {
        emit(Resource.of {
            repository.fetchGameScreenshots(id).map { response ->
                response.results.orEmpty().map { it.toDomain() }
            }
        })
    }
}