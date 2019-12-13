package com.example.moviesapp.presentation.fragments.movies.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.moviesapp.data.models.Movie
import javax.inject.Inject

/**
 * Created by
 * Mykhailo on 12/12/2019.
 */

class MoviesDiffCallback @Inject constructor() : DiffUtil.ItemCallback<Movie>() {

    override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
        return oldItem.title == newItem.title &&
                oldItem.overview == newItem.overview &&
                oldItem.backdropPath == newItem.backdropPath
    }
}