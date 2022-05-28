package com.ganeshgundu.nasaapod.network

import com.ganeshgundu.moviesapp.BuildConfig
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

private val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    private val retrofit = Retrofit.Builder()
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .baseUrl(BuildConfig.BASE_URL)
        .build()

    interface MoviesApiService {
        @GET("/3/movie/now_playing")
        suspend fun getMovies(@Query("api_key") api_key : String): Response<MoviesApiData>
    }


    object MoviesApiNetworkHelper {
        val moviesApiService: MoviesApiService = retrofit.create(MoviesApiService::class.java)
    }