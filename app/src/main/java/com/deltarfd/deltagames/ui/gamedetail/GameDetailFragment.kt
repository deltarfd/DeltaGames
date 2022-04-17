package com.deltarfd.deltagames.ui.gamedetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.deltarfd.deltagames.databinding.FragmentGameDetailBinding
import com.deltarfd.deltagames.ui.BaseFragment
import com.deltarfd.deltagames.utils.addPlatformIcons
import com.deltarfd.deltagames.utils.gone
import com.deltarfd.deltagames.utils.hideChildren
import com.deltarfd.deltagames.utils.load
import com.deltarfd.deltagames.utils.loadImage
import com.deltarfd.deltagames.utils.setMetascore
import com.deltarfd.deltagames.utils.showChildren
import com.deltarfd.deltagames.utils.visible
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.launch


@AndroidEntryPoint
class GameDetailFragment : BaseFragment() {

    private var _binding: FragmentGameDetailBinding? = null
    private val binding get() = _binding!!

    override val viewModel: GameDetailViewModel by viewModels()

    private val adapter by lazy { GameDetailScreenshotAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentGameDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.screenshots.adapter = adapter
        observe()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun observe() {
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch {
                    viewModel.uiState.filterNotNull().collect {
                        val detail = it.detail.info
                        val screenshots = it.detail.screenshots
                        binding.apply {
                            background.loadImage(detail.backgroundImage)
                            name.text = detail.name
                            releaseDate.text = detail.releaseDate
                            publisherName.text = detail.publisher
                            platforms.addPlatformIcons(icons = it.getPlatformIcons())
                            metascore.setMetascore(
                                detail.metascore,
                                it.getMetascoreColor()
                            )
                            genreChipGroup.load(detail.genres)
                            description.setBodyContent(detail.description)
                            rating.startAnimation(detail.rating)
                            adapter.submitList(screenshots)
                        }
                    }
                }
            }
        }
    }

    override fun onLoading() {
        super.onLoading()
        binding.apply {
            constraintLayout.hideChildren()
            loadingIcon.visible()
        }
    }

    override fun onLoaded() {
        super.onLoaded()
        binding.apply {
            constraintLayout.showChildren()
            loadingIcon.gone()
        }
    }
}