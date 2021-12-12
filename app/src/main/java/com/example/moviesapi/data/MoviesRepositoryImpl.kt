package com.example.moviesapi.data

import com.example.moviesapi.api.MoviesApi
import com.example.moviesapi.data.movie.Movie
import com.example.moviesapi.data.moviesTrending.MoviesTrendingList
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class MoviesRepositoryImpl
@Inject constructor(private val repositoriesMovies: MoviesApi) : MoviesRepository {
    override fun getMovies(): Single<MoviesTrendingList> =
        repositoriesMovies.fetchMoviesTrending()

    override fun getMovie(id: Int): Single<Movie> =
        repositoriesMovies.fetchMovie(id)
}