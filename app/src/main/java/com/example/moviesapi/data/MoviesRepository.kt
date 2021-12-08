package com.example.moviesapi.data

import com.example.moviesapi.api.MoviesApi
import com.example.moviesapi.api.MoviesApiFactory
import io.reactivex.rxjava3.core.Single

class MoviesRepository(private val repositoriesMovies: MoviesApi = MoviesApiFactory.create()) {
    fun getMovies(): Single<MoviesTrendingList> =
        repositoriesMovies.fetchMoviesTrending()
}