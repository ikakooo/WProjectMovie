package com.example.movieapp.network

import com.example.movieapp.constants.Constants
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

class Network{
    private val retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(Constants.BASE_URL_MOVIES)
        .build()
    val service: TopRatedMovies = retrofit.create(TopRatedMovies::class.java)

    interface TopRatedMovies{
        @GET("3/movie/top_rated")
        fun getTopRated(
            @Query("api_key") key: String,
            @Query("page") page: Int
        ): Call<TopMoviesResponse>


    }
}