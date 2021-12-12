package com.example.moviesapi.data.moviesTrending

import com.google.gson.annotations.SerializedName


class Movies(
    @SerializedName("id")
    val id: Int,

    @SerializedName("title")
    val title: String,

    @SerializedName("poster_path")
    val poster_path: String
)