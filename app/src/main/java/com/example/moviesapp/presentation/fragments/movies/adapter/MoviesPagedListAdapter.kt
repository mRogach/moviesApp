package com.example.moviesapp.presentation.fragments.movies.adapter

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.moviesapp.R
import com.example.moviesapp.data.models.Movie
import com.example.moviesapp.presentation.basics.adapter.BaseListenerViewHolder
import com.example.moviesapp.presentation.basics.adapter.BasePagedListAdapter
import javax.inject.Inject

/**
 * Created by
 * Mykhailo on 12/12/2019.
 */

class MoviesPagedListAdapter @Inject constructor(diffUtilCallback: DiffUtil.ItemCallback<Movie>) :
    BasePagedListAdapter<Movie, MoviesPagedListAdapter.MovieViewHolder>(diffUtilCallback) {

    override fun getItemViewId() = R.layout.item_movie

    override fun instantiateViewHolder(view: View?) = MovieViewHolder(view)

    inner class MovieViewHolder(itemView: View?) : BaseListenerViewHolder<Movie>(itemView) {

        private val tvTitle by lazy { itemView?.findViewById(R.id.tvTitle) as TextView? }
        private val tvRating by lazy { itemView?.findViewById(R.id.tvRating) as TextView? }
        private val ivMovieBackground by lazy { itemView?.findViewById(R.id.ivMovieBackground) as ImageView? }

        override fun onBind(item: Movie, onItemClickListener: OnItemClickListener?) {
            tvTitle?.text = item.title
            tvRating?.text = item.voteAverage.toString()
            ivMovieBackground?.let {

                Glide.with(it.context)
                    .load("https://image.tmdb.org/t/p/w500/" + item.backdropPath)
                    .centerCrop()
                    .diskCacheStrategy(DiskCacheStrategy.NONE)
                    .skipMemoryCache(true)
                    .into(it)
            }
        }

    }
}