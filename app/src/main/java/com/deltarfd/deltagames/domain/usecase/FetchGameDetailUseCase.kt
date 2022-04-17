package com.deltarfd.deltagames.domain.usecase

import com.deltarfd.deltagames.data.Resource
import com.deltarfd.deltagames.data.repository.GameDetailRepository
import com.deltarfd.deltagames.domain.mapper.toDomain
import com.deltarfd.deltagames.utils.map
import javax.inject.Inject
import kotlinx.coroutines.flow.flow

class FetchGameDetailUseCase @Inject constructor(private val repository: GameDetailRepository) {

    operator fun invoke(id: Int) = flow {
        emit(Resource.of { repository.fetchGameDetail(id).map { it.toDomain() } })
    }
}