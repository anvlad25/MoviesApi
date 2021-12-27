package com.example.moviesapi.data.movie

import com.google.gson.annotations.SerializedName

class Movie(
    @SerializedName("id")
    val id: Int,

    @SerializedName("title")
    val title: String,

    @SerializedName("poster_path")
    val poster_path: String,

    @SerializedName("overview")
    val overview: String
)