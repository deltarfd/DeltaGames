package com.deltarfd.deltagames.ui.gamelist

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.deltarfd.deltagames.databinding.ItemGameListBinding
import com.deltarfd.deltagames.domain.model.GameListItem
import com.deltarfd.deltagames.ui.gamelist.GameListAdapter.GameListViewHolder
import com.deltarfd.deltagames.utils.loadImage
import com.deltarfd.deltagames.utils.setVisibility

class GameListAdapter : PagingDataAdapter<GameListItem, GameListViewHolder>(DIFF) {

    var itemClickListener: (id: Int) -> Unit = {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GameListViewHolder {
        val itemView =
            ItemGameListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return GameListViewHolder(itemView, itemClickListener)
    }

    override fun onBindViewHolder(holder: GameListViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }

    override fun onViewRecycled(holder: GameListViewHolder) {
        super.onViewRecycled(holder)
        Glide.with(holder.itemBinding.root).clear(holder.itemBinding.background)
    }

    class GameListViewHolder(
        val itemBinding: ItemGameListBinding,
        private val itemClickListener: (Int) -> Unit
    ) :
        RecyclerView.ViewHolder(itemBinding.root) {

        fun bind(item: GameListItem) {

            itemBinding.apply {

                name.text = item.name
                background.loadImage(item.imageUrl)

                val isPcGame = item.platforms.any { it == "pc" }
                val isPsGame = item.platforms.any { it == "playstation" }
                val isXboxGame = item.platforms.any { it == "xbox" }
                val isNintendoGame = item.platforms.any { it == "nintendo" }

                pcPlatformIcon.setVisibility(isPcGame)
                psPlatformIcon.setVisibility(isPsGame)
                xboxPlatformIcon.setVisibility(isXboxGame)
                nintendoPlatformIcon.setVisibility(isNintendoGame)

                root.setOnClickListener {
                    itemClickListener(item.id)
                }
            }
        }
    }

    private object DIFF : DiffUtil.ItemCallback<GameListItem>() {

        override fun areItemsTheSame(oldItem: GameListItem, newItem: GameListItem): Boolean {
            return oldItem.id == newItem.id
        }

        @SuppressLint("DiffUtilEquals")
        override fun areContentsTheSame(oldItem: GameListItem, newItem: GameListItem): Boolean {
            return oldItem.id == newItem.id
        }
    }
}