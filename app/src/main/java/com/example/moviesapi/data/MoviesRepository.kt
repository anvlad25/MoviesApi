package com.example.moviesapi.data

import com.example.moviesapi.data.movie.Movie
import com.example.moviesapi.data.moviesTrending.MoviesTrendingList
import io.reactivex.rxjava3.core.Single

interface MoviesRepository {
    fun getMovies(): Single<MoviesTrendingList>
    fun getMovie(id: Int): Single<Movie>
}