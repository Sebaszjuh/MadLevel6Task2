package com.example.madlevel6task2.API

import com.example.madlevel6task2.BuildConfig
import com.example.madlevel6task2.model.Movies
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

const val API_KEY: String = BuildConfig.API_KEY
interface MovieApiService {

    /**
     * API is stored in the root of the project
     */
    @GET("discover/movie?api_key=$API_KEY")
    fun getMoviesByYear(@Query("primary_release_year") year: String): Call<Movies>
}

