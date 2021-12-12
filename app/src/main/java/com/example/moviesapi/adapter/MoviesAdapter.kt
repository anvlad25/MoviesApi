package com.example.moviesapi.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.moviesapi.App
import com.example.moviesapi.databinding.MoviesItemBinding

class MoviesAdapter(private val presenter: IMovieListPresenter) :
    RecyclerView.Adapter<MoviesAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(
            MoviesItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        ).apply {
            itemView.setOnClickListener { presenter.itemClickListener?.invoke(this) }
        }

    override fun getItemCount() = presenter.getCount()

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        presenter.bindView(holder.apply { pos = position })
    }



    inner class ViewHolder(private val viewBinding: MoviesItemBinding) :
        RecyclerView.ViewHolder(viewBinding.root),
        MoviesItemView {
        override var pos = -1

        override fun setMovie(text: String, poster: String) {
            with(viewBinding) {
                movieText.text = text
                Glide.with(App.instance)
                    .load("https://image.tmdb.org/t/p/w500$poster")
                    .override(800)
                    .into(movieImage)
            }
        }

    }
}