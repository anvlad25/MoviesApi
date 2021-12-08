package com.example.moviesapi.main

import android.os.Bundle
import com.example.moviesapi.databinding.MainActivityBinding
import moxy.MvpAppCompatActivity
import com.example.moviesapi.App
import com.example.moviesapi.R
import com.example.moviesapi.movies.MoviesScreen
import com.github.terrakok.cicerone.androidx.AppNavigator

class MainActivity : MvpAppCompatActivity(), MainView {

    private val navigator = AppNavigator(this, R.id.container)
    private var vb: MainActivityBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        vb = MainActivityBinding.inflate(layoutInflater)
        setContentView(vb?.root)

        if (savedInstanceState == null) {
            App.instance.router.newRootScreen(MoviesScreen)
        }
    }

    override fun onResumeFragments() {
        super.onResumeFragments()
        App.instance.navigatorHolder.setNavigator(navigator)
    }

    override fun onPause() {
        super.onPause()
        App.instance.navigatorHolder.removeNavigator()
    }
}