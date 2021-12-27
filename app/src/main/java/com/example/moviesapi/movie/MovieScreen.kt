package com.example.moviesapi.movie

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import com.github.terrakok.cicerone.androidx.FragmentScreen

class MovieScreen(private val id: Int) : FragmentScreen {
    override fun createFragment(factory: FragmentFactory): Fragment = MovieFragment.newInstance(id)
}