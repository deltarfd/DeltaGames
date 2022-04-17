package com.deltarfd.deltagames.ui.gamedetail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.deltarfd.deltagames.data.Resource
import com.deltarfd.deltagames.domain.model.GameDetail
import com.deltarfd.deltagames.domain.usecase.FetchGameDetailWithScreenshotsUseCase
import com.deltarfd.deltagames.ui.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@HiltViewModel
class GameDetailViewModel @Inject constructor(
    private val fetchGameDetailWithScreenshotsUseCase: FetchGameDetailWithScreenshotsUseCase,
    savedStateHandle: SavedStateHandle
) : BaseViewModel() {

    private val gameId = savedStateHandle.get<Int>("gameId") ?: 1

    private var _uiState = MutableStateFlow<GameDetailUiState?>(null)
    val uiState: StateFlow<GameDetailUiState?>
        get() = _uiState.asStateFlow()

    private var job: Job? = null

    init {
        fetchGameDetailWithScreenshots()
    }

    private fun fetchGameDetailWithScreenshots() {
        job = viewModelScope.launch {
            fetchGameDetailWithScreenshotsUseCase(gameId).collect {
                when (it) {
                    is Resource.Error -> onError(it.throwable)
                    is Resource.Loading -> loading()
                    is Resource.Success -> onSuccessNewGameDetail(it.data)
                }
            }
        }
    }

    private suspend fun onSuccessNewGameDetail(detail: GameDetail) {
        _uiState.emit(GameDetailUiState(detail))
        loading(false)
    }

    private fun onError(throwable: Throwable) {
        error(throwable) {
            job?.cancel()
            fetchGameDetailWithScreenshots()
        }
    }

    override fun onCleared() {
        super.onCleared()
        job?.cancel()
        job = null
    }
}