package com.ganeshgundu.nasaapod.repository

import com.ganeshgundu.nasaapod.network.MoviesApiData

data class MoviesRepositoryModel(
        val responseStatus: MoviesRepository.ResponseStatus,
        val response: MoviesApiData?
)