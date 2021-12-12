package com.example.moviesapi.di

import android.content.Context
import com.example.moviesapi.App
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.Router
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import javax.inject.Singleton

@Singleton
@Component(modules = [AndroidInjectionModule::class, MoviesModule::class])
interface MoviesApiComponent : AndroidInjector<App> {

    @Component.Builder
    interface Builder {
        fun build(): MoviesApiComponent

        @BindsInstance
        fun withContext(context: Context): Builder

        @BindsInstance
        fun withRouter(router: Router): Builder

        @BindsInstance
        fun withNavigatorHolder(navigatorHolder: NavigatorHolder): Builder
    }
}