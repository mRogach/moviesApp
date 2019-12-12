package com.example.moviesapp.data.api

import javax.inject.Inject

/**
 * Created by
 * Mykhailo on 12/12/2019.
 */

class MoviesAppRestClient @Inject constructor() : BaseRestClient() {

    lateinit var moviesService: MoviesService

    init {
        setUpRestClient()
    }

    private fun setUpRestClient() {
        val retrofit = getRetrofitBuilder().client(getOkHttpClient()).build()
        moviesService = retrofit.create(MoviesService::class.java)
    }
}