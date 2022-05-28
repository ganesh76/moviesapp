package com.ganeshgundu.nasaapod.viewmodel

import androidx.lifecycle.*
import com.ganeshgundu.nasaapod.repository.MoviesRepository
import com.ganeshgundu.nasaapod.repository.MoviesRepositoryModel
import kotlinx.coroutines.launch

class MoviesViewModel(val repository: MoviesRepository) : ViewModel() {

    val responseData = MutableLiveData<MoviesRepositoryModel>()
    val showLoading = MutableLiveData<Boolean>()

    fun getMovies() {
        showLoading.value = true
        viewModelScope.launch {
            val apodImageRepositoryModel = repository.getMoviesFromApi()
            showLoading.value = false
            responseData.value = apodImageRepositoryModel
        }
    }

    class MoviesViewModelFactory(private val repository: MoviesRepository) :
        ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(MoviesViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return MoviesViewModel(repository) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}
