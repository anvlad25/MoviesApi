package com.example.moviesapi.movie

import com.example.moviesapi.data.MoviesRepository
import com.github.terrakok.cicerone.Router
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import moxy.MvpPresenter

class MoviePresenter(
    private val id: Int?,
    private val moviesRepository: MoviesRepository,
    private val router: Router
) : MvpPresenter<MovieView>() {

    private val disposables = CompositeDisposable()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        id?.let { loadMovie(it) }
    }

    private fun loadMovie(id: Int) {
        disposables.add(
            moviesRepository
                .getMovie(id)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(
                    { movie -> viewState.updateList(movie) },
                    { error: Throwable ->
                        viewState.showError(
                            error
                        )
                    })
        )
    }

    fun backPressed(): Boolean {
        router.exit()
        return true
    }
}