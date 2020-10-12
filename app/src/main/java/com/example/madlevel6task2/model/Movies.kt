package com.example.madlevel6task2.model

import com.google.gson.annotations.SerializedName

data class Movies(
    @SerializedName("results")
    var movies: List<Movie>
)