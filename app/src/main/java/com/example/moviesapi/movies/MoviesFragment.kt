package com.example.moviesapi.movies

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.moviesapi.databinding.MoviesFragmentBinding
import com.example.moviesapi.BackButtonListener
import com.example.moviesapi.R
import com.example.moviesapi.abs.AbsFragment
import com.example.moviesapi.adapter.MoviesAdapter
import com.example.moviesapi.data.MoviesRepository
import com.example.moviesapi.data.MoviesRepositoryImpl
import moxy.ktx.moxyPresenter
import javax.inject.Inject

class MoviesFragment : AbsFragment(R.layout.movies_fragment), MoviesView, BackButtonListener {
    companion object {
        fun newInstance() = MoviesFragment()
    }

    @Inject
    lateinit var moviesRepository: MoviesRepository

    private var viewBinding: MoviesFragmentBinding? = null

    private var adapter: MoviesAdapter? = null

    private val presenter: MoviesPresenter by moxyPresenter {
        MoviesPresenter(
            moviesRepository,
            router
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

    override fun init() {
        viewBinding?.let {
            it.recyclerViewMovies.layoutManager = LinearLayoutManager(context)
            adapter = MoviesAdapter(presenter.moviesListPresenter)
            it.recyclerViewMovies.adapter = adapter
        }

    }

    @SuppressLint("NotifyDataSetChanged")
    override fun updateList() {
        adapter?.notifyDataSetChanged()
    }

    override fun showError(error: Throwable) {
        Toast.makeText(requireContext(), error.message, Toast.LENGTH_LONG).show()
    }

    override fun backPressed(): Boolean = presenter.backPressed()
}