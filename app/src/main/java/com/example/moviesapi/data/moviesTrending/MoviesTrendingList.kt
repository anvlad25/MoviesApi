package com.example.moviesapi.data.moviesTrending

import com.google.gson.annotations.SerializedName


class MoviesTrendingList(
    @SerializedName("results")
    val results: List<Movies>
)