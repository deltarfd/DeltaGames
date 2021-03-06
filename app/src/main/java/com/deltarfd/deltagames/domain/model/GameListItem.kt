package com.deltarfd.deltagames.domain.model

data class GameListItem(
    val id: Int,
    val name: String,
    val imageUrl: String,
    val platforms: List<String>
)