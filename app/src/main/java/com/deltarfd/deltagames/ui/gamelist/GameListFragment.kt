package com.deltarfd.deltagames.ui.gamelist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import com.deltarfd.deltagames.databinding.FragmentGameListBinding
import com.deltarfd.deltagames.ui.BaseFragment
import com.deltarfd.deltagames.utils.gone
import com.deltarfd.deltagames.utils.invisible
import com.deltarfd.deltagames.utils.visible
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.launch

@AndroidEntryPoint
class GameListFragment : BaseFragment() {

    private var _binding: FragmentGameListBinding? = null
    private val binding get() = _binding!!

    override val viewModel: GameListViewModel by viewModels()

    private val adapter = GameListAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = _binding ?: FragmentGameListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.gameList.adapter = adapter
        adapter.itemClickListener = {
            navigateToGameDetail(it)
        }

        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch {
                    viewModel.games.filterNotNull().collect {
                        adapter.submitData(lifecycle, it)
                    }
                }

                launch {
                    adapter.loadStateFlow.collectLatest { state ->
                        if (state.refresh is LoadState.Error) {
                            val throwable = (state.refresh as LoadState.Error).error
                            viewModel.error(throwable) {
                                viewModel.fetchGames()
                            }
                        } else if (state.append is LoadState.Loading) {
                            viewModel.loading(false)
                        }
                    }
                }
            }
        }
    }

    override fun onLoaded() {
        super.onLoaded()
        binding.apply {
            loadingIcon.gone()
            gameList.visible()
        }
    }

    override fun onLoading() {
        super.onLoading()
        binding.apply {
            loadingIcon.visible()
            gameList.invisible()
        }
    }

    private fun navigateToGameDetail(id: Int) {
        val action = GameListFragmentDirections.showGameDetail(id)
        findNavController().navigate(action)
    }
}