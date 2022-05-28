package com.ganeshgundu.nasaapod.network

import com.ganeshgundu.moviesapp.api.MovieResult
import com.squareup.moshi.Json
import java.io.Serializable

data class MoviesApiData(
    @Json(name = "results") val results: List<MovieResult>,
    @Json(name = "page") val page: String
) : Serializable