package com.example.madlevel6task2.Repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.madlevel6task2.API.MovieAPI
import com.example.madlevel6task2.API.MovieApiService
import com.example.madlevel6task2.model.Movie
import com.example.madlevel6task2.model.Movies
import kotlinx.coroutines.withTimeout
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TriviaRepository {
    private val movieApiService: MovieApiService = MovieAPI.createApi()

    private val _movies: MutableLiveData<List<Movie>> = MutableLiveData()


    private val _loading = MutableLiveData(false)

    val loading: LiveData<Boolean>
        get() = _loading

    val movies: LiveData<List<Movie>>
        get() = _movies

    suspend fun getMoviesByYear(year: String) {
        try {
            _loading.value = true
            //timeout the request after 5 seconds
            withTimeout(5_000) {
                movieApiService.getMoviesByYear(year).enqueue(object : Callback<Movies> {
                    override fun onResponse(call: Call<Movies>, response: Response<Movies>) {
                        if (response.isSuccessful) _movies.value = response.body()?.movies
                        _loading.value = false
                    }

                    override fun onFailure(call: Call<Movies>, t: Throwable) {
                        _loading.value = false
                    }
                })
            }
        } catch (error: Throwable) {
            throw MoviesRefreshError("Unable to refresh movies", error)
        }
    }

    class MoviesRefreshError(message: String, cause: Throwable) : Throwable(message, cause)

}
