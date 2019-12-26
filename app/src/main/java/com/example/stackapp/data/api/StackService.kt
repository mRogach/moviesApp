package com.example.stackapp.data.api

import com.example.stackapp.data.models.TagsResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by
 * Mykhailo on 12/12/2019.
 */

interface StackService {

    @GET("tags")
    fun getTags(
        @Query("page") page: Int,
        @Query("site") site: String,
        @Query("order") order: String
    ): Observable<TagsResponse>
}