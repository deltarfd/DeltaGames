package com.deltarfd.deltagames.ui.home

import androidx.lifecycle.viewModelScope
import com.deltarfd.deltagames.core.domain.model.SearchParams
import com.deltarfd.deltagames.data.Resource
import com.deltarfd.deltagames.domain.model.GameImage
import com.deltarfd.deltagames.domain.usecase.FetchGamesBackgroundImagesUseCase
import com.deltarfd.deltagames.ui.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val fetchGamesBackgroundImagesUseCase: FetchGamesBackgroundImagesUseCase
) : BaseViewModel() {

    private val _images: MutableStateFlow<List<GameImage>?> = MutableStateFlow(null)
    val images: StateFlow<List<GameImage>?> = _images.asStateFlow()

    private val _searchParams: MutableSharedFlow<SearchParams> = MutableSharedFlow()
    val searchParams: SharedFlow<SearchParams> = _searchParams.asSharedFlow()

    private val gameIds = listOf(
        3498,
        3328,
        4200,
        5286,
        5679,
        4291,
        12020,
        13536,
        4062,
        3439,
        802,
        28,
        13537,
        4286
    )

    init {
        fetchGameImages()
    }

    private fun fetchGameImages() {
        viewModelScope.launch {
            fetchGamesBackgroundImagesUseCase(gameIds).collect {
                when (it) {
                    is Resource.Error -> error(it.throwable) {
                        cancel()
                        fetchGameImages()
                    }
                    is Resource.Loading -> _images.emit(it.data.orEmpty())
                    is Resource.Success -> _images.emit(it.data)
                }
            }
        }
    }

    fun searchPopularGamesIn2021() {
        viewModelScope.launch {
            _searchParams.emit(
                SearchParams(
                    search = "",
                    ordering = "-metacritic,-rating",
                    dates = "2021-01-01,2021-12-31"
                )
            )
        }

    }

    fun seeMoreGames() {
        viewModelScope.launch {
            _searchParams.emit(
                SearchParams(
                    search = "",
                    ordering = "",
                    dates = ""
                )
            )
        }
    }

    fun searchBestGamesOfTheYear() {
        viewModelScope.launch {
            _searchParams.emit(
                SearchParams(
                    search = "",
                    ordering = "-added",
                    dates = "2022-01-01,2022-12-31"
                )
            )
        }
    }

    fun searchQuery(query: String) {
        viewModelScope.launch {
            _searchParams.emit(
                SearchParams(
                    search = query,
                    ordering = "",
                    dates = ""
                )
            )
        }
    }
}