package com.ganeshgundu.moviesapp.app

import android.app.Application
import com.ganeshgundu.nasaapod.repository.MoviesRepository

class MoviesApp : Application() {
    val repository by lazy { MoviesRepository(this) }
}