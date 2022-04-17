package com.deltarfd.deltagames.domain.usecase

import com.deltarfd.deltagames.data.Resource
import com.deltarfd.deltagames.data.repository.GameDetailRepository
import com.deltarfd.deltagames.domain.mapper.toGameImage
import com.deltarfd.deltagames.domain.model.GameImage
import com.deltarfd.deltagames.utils.map
import javax.inject.Inject
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onStart

class FetchGamesBackgroundImagesUseCase @Inject constructor(private val repository: GameDetailRepository) {
    operator fun invoke(ids: List<Int>) = flow<Resource<List<GameImage>>> {
        val images = ids.map { id ->
            repository.fetchGameDetail(id).map { it.toGameImage() }.body()
        }
        emit(Resource.Success(images.filterNotNull()))
    }.onStart { emit(Resource.Loading(ids.map { GameImage(it, "") })) }
        .catch { emit(Resource.Error(it)) }
}