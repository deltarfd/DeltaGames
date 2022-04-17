package com.deltarfd.deltagames.ui.gamedetail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.deltarfd.deltagames.databinding.ItemGameDetailScreenshotBinding
import com.deltarfd.deltagames.domain.model.GameImage
import com.deltarfd.deltagames.ui.gamedetail.GameDetailScreenshotAdapter.GameDetailScreenshotViewHolder
import com.deltarfd.deltagames.utils.loadImage

class GameDetailScreenshotAdapter :
    ListAdapter<GameImage, GameDetailScreenshotViewHolder>(DIFF) {


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): GameDetailScreenshotViewHolder {
        val binding = ItemGameDetailScreenshotBinding.inflate(LayoutInflater.from(parent.context))
        return GameDetailScreenshotViewHolder(binding)
    }

    override fun onBindViewHolder(holder: GameDetailScreenshotViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class GameDetailScreenshotViewHolder(private val binding: ItemGameDetailScreenshotBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: GameImage) {
            binding.screenshot.loadImage(item.imageUrl)
        }
    }

    private object DIFF : DiffUtil.ItemCallback<GameImage>() {
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
            return oldItem.id == newItem.id
        }
    }
}