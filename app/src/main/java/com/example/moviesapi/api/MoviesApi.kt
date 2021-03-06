package com.example.moviesapi.api

import com.example.moviesapi.data.movie.Movie
import com.example.moviesapi.data.moviesTrending.MoviesTrendingList
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface MoviesApi {
    @GET("/3/trending/movie/week")
    fun fetchMoviesTrending(@Query("language") language: String = "ru"): Single<MoviesTrendingList>

    @GET("3/movie/{id}")
    fun fetchMovie(
        @Path("id") id: Int,
        @Query("language") language: String = "ru"
    ): Single<Movie>
}