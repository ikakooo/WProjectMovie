package com.example.movieapp.repository

import com.example.movieapp.constants.Constants
import com.example.movieapp.network.Network
import com.example.movieapp.network.TopMoviesResponse
import io.reactivex.rxjava3.core.Single
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException

class Repository(private val request:Network) {
    fun getMovies(): Single<TopMoviesResponse> {
        return Single.create { observer ->
            request.service.getTopRated(Constants.API_KEY, 1)
                .enqueue(object : Callback<TopMoviesResponse> {
                    override fun onFailure(call: Call<TopMoviesResponse>, t: Throwable) {
                        val e = IOException("Network error")
                        observer.onError(e)
                    }

                    override fun onResponse(
                        call: Call<TopMoviesResponse>,
                        response: Response<TopMoviesResponse>
                    ) {
                        val movies = response.body()!!
                        observer.onSuccess(movies)
                    }
                })
        }

    }
}
