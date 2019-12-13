package com.example.moviesapp.data.models

import com.google.gson.annotations.SerializedName

/**
 * Created by
 * Mykhailo on 12/13/2019.
 */
data class MoviesResponse(
    @SerializedName("results")
    val results: List<Movie>,
    @SerializedName("total_pages")
    val totalPages: Int
)