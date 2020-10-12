package com.example.madlevel6task2.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Movie(
    @SerializedName("title") var title: String,
    @SerializedName("release_date") var releaseDate: String,
    @SerializedName("overview") var overview: String,
    @SerializedName("vote_average") var rating: Double,
    @SerializedName("poster_path") var poster: String,
    @SerializedName("backdrop_path") var backdrop: String
) : Parcelable {
    fun getPosterUrl() = "https://image.tmdb.org/t/p/original$poster"
    fun getBackdropUrl() = "https://image.tmdb.org/t/p/original$backdrop"
}