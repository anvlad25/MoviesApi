package com.example.moviesapi.movies

import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface MoviesView : MvpView {
    fun init()
    fun updateList()
    fun showError(error: Throwable)
    fun showEny(bla: String)
}