package com.example.moviesapi.main

import android.os.Bundle
import com.example.moviesapi.databinding.MainActivityBinding
import com.example.moviesapi.R
import com.example.moviesapi.abs.AbsActivity
import com.example.moviesapi.movies.MoviesScreen
import com.github.terrakok.cicerone.androidx.AppNavigator

class MainActivity : AbsActivity(), MainView {

    private val navigator = AppNavigator(this, R.id.container)
    private var vb: MainActivityBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        vb = MainActivityBinding.inflate(layoutInflater)
        setContentView(vb?.root)

        if (savedInstanceState == null) {
            router.newRootScreen(MoviesScreen)
        }
    }

    override fun onResumeFragments() {
        super.onResumeFragments()
        navigatorHolder.setNavigator(navigator)
    }

    override fun onPause() {
        super.onPause()
        navigatorHolder.removeNavigator()
    }
}