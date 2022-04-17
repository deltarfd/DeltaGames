package com.deltarfd.deltagames.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.deltarfd.deltagames.databinding.ItemHomeGameListBinding
import com.deltarfd.deltagames.domain.model.GameImage
import com.deltarfd.deltagames.utils.gone
import com.deltarfd.deltagames.utils.invisible
import com.deltarfd.deltagames.utils.loadImageWithCallback
import com.deltarfd.deltagames.utils.visible

class HomeGamesAdapter :
    ListAdapter<GameImage, HomeGamesAdapter.ViewHolder>(DIFF) {

    var imageLoadedListener: () -> Unit = {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemHomeGameListBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class ViewHolder(val binding: ItemHomeGameListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: GameImage) {
            if (item.imageUrl.isEmpty()) {
                binding.shimmerBackground.visible()
                binding.homeScreenGameImage.invisible()
            } else {
                binding.homeScreenGameImage.loadImageWithCallback(item.imageUrl, success = {
                    binding.shimmerBackground.gone()
                    binding.homeScreenGameImage.visible()
                    imageLoadedListener()
                })
            }
        }
    }

    object DIFF : DiffUtil.ItemCallback<GameImage>() {
        override fun areItemsTheSame(
            oldItem: GameImage,
            newItem: GameImage
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: GameImage,
            newItem: GameImage
        ): Boolean {
            return oldItem.toString() == newItem.toString()
        }

    }
}