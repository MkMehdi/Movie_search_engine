package com.sample.moviesearchengine.api

import com.sample.moviesearchengine.model.Page
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieSearchApi {

    @GET("/search/movie?api_key=b2168bae3a2c67509eb6b97572f521c2")
    suspend fun searchMovies(@Query("query") query: String): Response<List<Page>>
}