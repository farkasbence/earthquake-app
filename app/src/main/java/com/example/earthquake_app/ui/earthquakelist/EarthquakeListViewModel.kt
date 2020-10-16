package com.example.earthquake_app.ui.earthquakelist

import android.app.Application
import androidx.lifecycle.*
import com.example.earthquake_app.database.getDatabase
import com.example.earthquake_app.repository.EarthquakeRepository
import kotlinx.coroutines.launch
import java.io.IOException
import java.lang.IllegalArgumentException

class EarthquakeListViewModel(application: Application): AndroidViewModel(application) {

    private val earthquakeRepository = EarthquakeRepository(getDatabase(application))

    private var _isNetworkError = MutableLiveData<Boolean>(false)

    val isNetworkError: LiveData<Boolean>
        get() = _isNetworkError

    val earthquakeList = earthquakeRepository.earthquakes

    init {
        refreshDataFromRepository()
    }

    private fun refreshDataFromRepository() = viewModelScope.launch {

        try {
            earthquakeRepository.refreshEarthquakes()
        } catch (networkError: IOException) {
            if (earthquakeList.value.isNullOrEmpty()) {
                _isNetworkError.value = true
            }
        }
    }

    class Factory(val app: Application): ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(EarthquakeListViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return EarthquakeListViewModel(app) as T
            }
            throw IllegalArgumentException("Unable to construct viewmodel")
        }
    }
}