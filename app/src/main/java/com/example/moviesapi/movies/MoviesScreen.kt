package com.example.moviesapi.movies

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import com.github.terrakok.cicerone.androidx.FragmentScreen

object MoviesScreen : FragmentScreen {
    override fun createFragment(factory: FragmentFactory): Fragment = MoviesFragment.newInstance()
}