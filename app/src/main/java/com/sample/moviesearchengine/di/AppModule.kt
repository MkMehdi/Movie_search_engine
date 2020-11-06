package com.sample.moviesearchengine.di

import com.sample.moviesearchengine.api.MovieSearchApi
import com.sample.moviesearchengine.api.MoviesDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(ApplicationComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideRetrofit() : Retrofit = Retrofit.Builder()
        .baseUrl("https://developers.themoviedb.org/3/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    @Provides
    fun provideUserTaskApi(retrofit: Retrofit): MovieSearchApi = retrofit.create(MovieSearchApi::class.java)

    @Singleton
    @Provides
    fun provideUserTaskDataSource(movieSearchApi: MovieSearchApi) = MoviesDataSource(movieSearchApi)

}