package com.sample.moviesearchengine.api

import android.util.Log
import com.sample.moviesearchengine.utils.Progress
import retrofit2.Response

abstract class BaseDataSource {

    protected suspend fun <T> getResult(call: suspend () -> Response<T>): Progress<T> {
        try {
            val response = call()
            if (response.isSuccessful) {
                val body = response.body()
                if (body != null) return Progress.success(body)
            }
            return error(" ${response.code()} ${response.message()}")
        } catch (e: Exception) {
            return error(e.message ?: e.toString())
        }
    }

    private fun <T> error(message: String): Progress<T> {
        Log.e("remoteDataSource", message)
        return Progress.error("Network call has failed for a following reason: $message")
    }

}