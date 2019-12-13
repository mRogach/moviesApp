package com.example.moviesapp.data.api

import com.example.moviesapp.data.models.MoviesResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by
 * Mykhailo on 12/12/2019.
 */

interface MoviesService {

    @GET("movie/popular")
    fun getPopularMovies(
        @Query("api_key") apiKey: String,
        @Query("page") page: Int
    ): Observable<MoviesResponse>
}