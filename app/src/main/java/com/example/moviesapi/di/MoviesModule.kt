package com.example.moviesapi.di

import com.example.moviesapi.data.MoviesRepository
import com.example.moviesapi.data.MoviesRepositoryImpl
import com.example.moviesapi.main.MainActivity
import com.example.moviesapi.movie.MovieFragment
import com.example.moviesapi.movies.MoviesFragment
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module(includes = [NetworkModule::class])
interface MoviesModule {
    @ContributesAndroidInjector
    fun bindMovieFragment(): MovieFragment

    @ContributesAndroidInjector
    fun bindMoviesFragment(): MoviesFragment

    @ContributesAndroidInjector
    fun bindMainActivity(): MainActivity

    @Binds
    fun bindMoviesRepository(
        moviesRepository: MoviesRepositoryImpl
    ): MoviesRepository
}