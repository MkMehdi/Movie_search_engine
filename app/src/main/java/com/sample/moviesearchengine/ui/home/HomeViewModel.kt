package com.sample.moviesearchengine.ui.home

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.sample.moviesearchengine.api.MoviesDataSource
import com.sample.moviesearchengine.model.Page
import com.sample.moviesearchengine.utils.Progress

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job

class HomeViewModel @ViewModelInject constructor(
    private val moviesDataSource: MoviesDataSource): ViewModel() {

    var jobApi = Job()
    val ioScope = CoroutineScope(Dispatchers.IO + jobApi)


    suspend fun getMoviesByTitle(title:String): Progress<List<Page>>{
        return moviesDataSource.searchMovie(title)
    }


    override fun onCleared() {
        super.onCleared()
        jobApi.cancel()
    }
}