package com.example.moviesapi.movies

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.moviesapi.databinding.MoviesFragmentBinding
import com.example.moviesapi.App
import com.example.moviesapi.BackButtonListener
import com.example.moviesapi.adapter.MoviesAdapter
import com.example.moviesapi.data.MoviesRepository
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class MoviesFragment : MvpAppCompatFragment(), MoviesView, BackButtonListener {

    private var viewBinding: MoviesFragmentBinding? = null
    private var adapter: MoviesAdapter? = null

    //private val viewBinding: MoviesFragmentBinding by viewBinding()
    private val presenter: MoviesPresenter by moxyPresenter {
        MoviesPresenter(
            MoviesRepository(),
            App.instance.router
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) =
        MoviesFragmentBinding.inflate(inflater, container, false).also {
            viewBinding = it
        }.root

    companion object {
        fun newInstance() = MoviesFragment()
    }

    override fun init() {
        viewBinding?.let {
            it.recyclerViewMovies.layoutManager = LinearLayoutManager(context)
            adapter = MoviesAdapter(presenter.moviesListPresenter)
            it.recyclerViewMovies.adapter = adapter
        }
    }

    override fun backPressed(): Boolean = presenter.backPressed()

}