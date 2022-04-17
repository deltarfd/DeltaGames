package com.deltarfd.deltagames.ui.gamelist

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.deltarfd.deltagames.core.domain.model.SearchParams
import com.deltarfd.deltagames.domain.model.GameListItem
import com.deltarfd.deltagames.domain.usecase.FetchGameListUseCase
import com.deltarfd.deltagames.ui.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import javax.inject.Inject
import kotlinx.coroutines.launch

@HiltViewModel
class GameListViewModel @Inject constructor(
    private val fetchGameListUseCase: FetchGameListUseCase,
    private val savedStateHandle: SavedStateHandle
) : BaseViewModel() {

    private var _games: MutableStateFlow<PagingData<GameListItem>?> =
        MutableStateFlow(null)
    val games: StateFlow<PagingData<GameListItem>?> = _games.asStateFlow()

    init {
        fetchGames()
    }

    fun fetchGames() {
        viewModelScope.launch {
            val searchParams = savedStateHandle.get<SearchParams>("searchParams")
            searchParams?.apply {
                fetchGameListUseCase(
                    search = search,
                    ordering = ordering,
                    dates = dates
                ).cachedIn(viewModelScope)
                    .onStart { loading() }
                    .catch { error(it) { fetchGames() } }
                    .collect { _games.emit(it) }
            }
        }
    }
}