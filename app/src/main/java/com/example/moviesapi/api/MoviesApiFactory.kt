package com.example.moviesapi.api

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object MoviesApiFactory {
    private val gson: Gson =
        GsonBuilder()
            .setPrettyPrinting()
            .create()

    fun create(): MoviesApi =
        Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org")
            .client(
                OkHttpClient.Builder()
                    .addInterceptor(MoviesApiInterceptor)
                    //.addInterceptor(GitHubApiErrorInterceptor)
                    .addNetworkInterceptor(
                        HttpLoggingInterceptor().apply {
                            level = HttpLoggingInterceptor.Level.BODY
                        }
                    )
                    .build()
            )
            .addCallAdapterFactory(RxJava3CallAdapterFactory.createSynchronous())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(MoviesApi::class.java)
}