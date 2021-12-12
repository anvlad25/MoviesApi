package com.example.moviesapi.movie

import com.example.moviesapi.data.movie.Movie
import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface MovieView : MvpView {
    fun updateList(movie: Movie)
    fun showError(error: Throwable)
}