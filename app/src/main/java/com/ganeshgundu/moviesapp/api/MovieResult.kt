package com.ganeshgundu.moviesapp.api

import com.squareup.moshi.Json
import java.io.Serializable

data class MovieResult(
    @Json(name = "id") val id: String,
    @Json(name = "original_title") val title: String,
    @Json(name = "overview") val overView: String,
    @Json(name = "popularity") val popularity: String?,
    @Json(name = "poster_path") val posterPath: String,
    @Json(name = "release_date") val releaseDate: String,
    @Json(name = "vote_average") val voteAverage: String
) : Serializable