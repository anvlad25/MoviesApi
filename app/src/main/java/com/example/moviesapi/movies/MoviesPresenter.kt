package com.example.moviesapi.movies

import com.example.moviesapi.adapter.IMovieListPresenter
import com.example.moviesapi.adapter.MoviesItemView
import com.example.moviesapi.data.Movies
import com.example.moviesapi.data.MoviesRepository
import com.github.terrakok.cicerone.Router
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import moxy.MvpPresenter

class MoviesPresenter(private val moviesRepository: MoviesRepository, private val router: Router) :
    MvpPresenter<MoviesView>() {
    class MoviesListPresenter : IMovieListPresenter {
        val movies = mutableListOf<Movies>()
        override var itemClickListener: ((MoviesItemView) -> Unit)? = null

        override fun getCount() = movies.size

        override fun bindView(view: MoviesItemView) {
            val movie = movies[view.pos]
            view.setMovie(movie.title, movie.poster_path)
        }
    }

    val moviesListPresenter = MoviesListPresenter()
    private val disposables = CompositeDisposable()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
        loadMoviesTrending()
    }

    fun backPressed(): Boolean {
        router.exit()
        return true
    }

    private fun loadMoviesTrending() {
        disposables.add(
            moviesRepository
                .getMovies()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(
                    { moviesList ->
                        moviesListPresenter.movies.addAll(moviesList.results)
                        viewState.updateList()
                        //viewState.showEny(moviesList.results[0].title)
                    },
                    { error: Throwable ->
                        viewState.showError(
                            error
                        )
                    })
        )
    }
}