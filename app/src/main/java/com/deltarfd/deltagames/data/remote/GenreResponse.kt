package com.deltarfd.deltagames.data.remote

import com.google.gson.annotations.SerializedName

class GenreResponse(
    @SerializedName("games_count")
    val gamesCount: Int? = null,
    @SerializedName("id")
    val id: Int? = null,
    @SerializedName("image_background")
    val imageBackground: String? = null,
    @SerializedName("name")
    val name: String? = null,
    @SerializedName("slug")
    val slug: String? = null
)