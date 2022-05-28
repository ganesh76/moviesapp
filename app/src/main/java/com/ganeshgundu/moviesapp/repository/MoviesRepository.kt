package com.ganeshgundu.nasaapod.repository

import android.content.Context
import com.ganeshgundu.moviesapp.BuildConfig
import com.ganeshgundu.nasaapod.network.MoviesApiNetworkHelper


class MoviesRepository(val context: Context) {

    enum class ResponseStatus() {
        API_ERROR,
        SUCCESS
    }

    suspend fun getMoviesFromApi(): MoviesRepositoryModel {
        val response = MoviesApiNetworkHelper.moviesApiService.getMovies(BuildConfig.API_KEY)
        response.body()?.let {
            if (response.isSuccessful) {
                return MoviesRepositoryModel(ResponseStatus.SUCCESS, response.body()!!)
            } else {
                return MoviesRepositoryModel(ResponseStatus.API_ERROR, null)
            }
        } ?: return MoviesRepositoryModel(ResponseStatus.API_ERROR, null)
    }

}