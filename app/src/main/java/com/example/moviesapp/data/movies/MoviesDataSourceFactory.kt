package com.example.moviesapp.data.movies

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.example.moviesapp.data.api.MoviesAppRestClient
import com.example.moviesapp.data.models.Movie
import javax.inject.Inject

/**
 * Created by
 * Mykhailo on 12/12/2019.
 */

class MoviesDataSourceFactory @Inject constructor(private val moviesAppRestClient: MoviesAppRestClient): DataSource.Factory<Int, Movie>() {
    val sourceLiveData = MutableLiveData<MoviesDataSource>().apply { MoviesDataSource(moviesAppRestClient) }
    private lateinit var latestSource: MoviesDataSource
    override fun create(): DataSource<Int, Movie> {
        latestSource = MoviesDataSource(moviesAppRestClient)
        sourceLiveData.postValue(latestSource)
        return latestSource
    }
}