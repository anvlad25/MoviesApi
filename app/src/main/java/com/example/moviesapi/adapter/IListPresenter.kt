package com.example.moviesapi.adapter

interface IListPresenter<V : IItemView> {
    var itemClickListener: ((V) -> Unit)?
    fun bindView(view: V)
    fun getCount(): Int
}

interface IMovieListPresenter : IListPresenter<MoviesItemView>