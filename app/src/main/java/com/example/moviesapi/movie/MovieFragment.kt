package com.example.moviesapi.movie

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import com.bumptech.glide.Glide
import com.example.moviesapi.BackButtonListener
import com.example.moviesapi.R
import com.example.moviesapi.abs.AbsFragment
import com.example.moviesapi.arguments
import com.example.moviesapi.data.movie.Movie
import com.example.moviesapi.data.MoviesRepository
import com.example.moviesapi.databinding.MovieFragmetnBinding
import moxy.ktx.moxyPresenter
import javax.inject.Inject

class MovieFragment : AbsFragment(R.layout.movie_fragmetn), MovieView, BackButtonListener {

    companion object {
        private const val ARG_MOVIE_ID = "arg_movie_id"
        fun newInstance(id: Int) = MovieFragment().arguments(ARG_MOVIE_ID to id)
    }

    @Inject
    lateinit var moviesRepository: MoviesRepository

    private val id by lazy {
        arguments?.getInt(ARG_MOVIE_ID)
    }

    private var viewBinding: MovieFragmetnBinding? = null

    private val presenter: MoviePresenter by moxyPresenter {
        MoviePresenter(
            id,
            moviesRepository,
            router
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) =
        MovieFragmetnBinding.inflate(inflater, container, false).also {
            viewBinding = it
        }.root

    override fun updateList(movie: Movie) {
        viewBinding?.let {
            it.textMovie.text = movie.overview

            Glide.with(this)
                .load("https://image.tmdb.org/t/p/w500${movie.poster_path}")
                .override(900)
                .into(it.imageMovie)
        }
    }

    override fun showError(error: Throwable) {
        Toast.makeText(requireContext(), error.message, Toast.LENGTH_LONG).show()
    }

    override fun backPressed(): Boolean = presenter.backPressed()
}