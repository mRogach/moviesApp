package com.example.moviesapp.presentation.application.di

import androidx.paging.DataSource
import androidx.recyclerview.widget.DiffUtil
import com.example.moviesapp.data.models.Movie
import com.example.moviesapp.data.movies.MoviesDataSourceFactory
import com.example.moviesapp.presentation.basics.adapter.BasePagedListAdapter
import com.example.moviesapp.presentation.fragments.movies.adapter.MoviesDiffCallback
import com.example.moviesapp.presentation.fragments.movies.adapter.MoviesPagedListAdapter
import dagger.Module
import dagger.Provides

/**
 * Created by
 * Mykhailo on 12/12/2019.
 */

@Module
class MoviesDataSourceModule {

    @Provides
    fun provideMoviesDataSourceFactory(commentsDataSourceFactory: MoviesDataSourceFactory): DataSource.Factory<Int, Movie> =
        commentsDataSourceFactory

    @Provides
    fun provideDiffUtilCallback(diffUtilCallback: MoviesDiffCallback): DiffUtil.ItemCallback<Movie> =
        diffUtilCallback

    @Provides
    fun provideAdapter(moviesAdapter: MoviesPagedListAdapter): BasePagedListAdapter<Movie, MoviesPagedListAdapter.MovieViewHolder> =
        moviesAdapter
}