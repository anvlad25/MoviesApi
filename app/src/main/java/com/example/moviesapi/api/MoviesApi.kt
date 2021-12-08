package com.example.moviesapi.api

import com.example.moviesapi.data.MoviesTrendingList
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Query


interface MoviesApi {
    @GET("/3/trending/movie/week")
    fun fetchMoviesTrending(@Query("language") language: String = "ru"): Single<MoviesTrendingList>
}