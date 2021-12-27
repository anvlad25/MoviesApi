package com.example.moviesapi.di

import com.example.moviesapi.api.MoviesApi
import com.example.moviesapi.api.MoviesApiInterceptor
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.Reusable
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

@Module
class NetworkModule {

    @Provides
    fun provideGson(): Gson =
        GsonBuilder()
            .setPrettyPrinting()
            .create()

    @Reusable
    @Provides
    fun provideGetMoviesApi(gson: Gson): MoviesApi =
        Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org")
            .client(
                OkHttpClient.Builder()
                    .addInterceptor(MoviesApiInterceptor)
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