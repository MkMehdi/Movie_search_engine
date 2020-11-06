package com.sample.moviesearchengine

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MovieSearchApp: Application() {

    override fun onCreate() {
        super.onCreate()
    }
}