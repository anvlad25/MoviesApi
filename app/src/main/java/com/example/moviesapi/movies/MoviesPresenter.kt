package com.example.moviesapi.movies

import com.example.moviesapi.adapter.IMovieListPresenter
import com.example.moviesapi.adapter.MoviesItemView
import com.example.moviesapi.data.Movies
import com.example.moviesapi.data.MoviesRepository
import com.github.terrakok.cicerone.Router
import moxy.MvpPresenter

class MoviesPresenter(private val moviesRepository: MoviesRepository, private val router: Router) :
    MvpPresenter<MoviesView>() {
    class MoviesListPresenter : IMovieListPresenter {
        private val movies = mutableListOf<Movies>(Movies(1,"Title1", "Path1"))
        override var itemClickListener: ((MoviesItemView) -> Unit)? = null

        override fun getCount() = movies.size

        override fun bindView(view: MoviesItemView) {
            val movie = movies[view.pos]
            view.setMovie(movie.title, movie.poster_path)
        }
    }

    val moviesListPresenter = MoviesListPresenter()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
    }

    fun backPressed(): Boolean {
        router.exit()
        return true
    }
}