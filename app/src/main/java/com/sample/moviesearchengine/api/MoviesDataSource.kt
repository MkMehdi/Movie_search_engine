package com.sample.moviesearchengine.api

import javax.inject.Inject

class MoviesDataSource @Inject constructor(
    private val movieSearchApi: MovieSearchApi
): BaseDataSource() {

    suspend fun searchMovie(query: String) = getResult { movieSearchApi.searchMovies(query) }

}