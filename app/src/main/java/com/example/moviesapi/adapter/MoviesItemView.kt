package com.example.moviesapi.adapter

interface MoviesItemView : IItemView {
    fun setMovie(text: String, poster: String)
}