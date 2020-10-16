package com.example.earthquake_app.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.example.earthquake_app.database.EarthquakeDatabase
import com.example.earthquake_app.database.asDomainModel
import com.example.earthquake_app.dto.asDatabaseModel
import com.example.earthquake_app.model.EarthquakeModel
import com.example.earthquake_app.network.EarthquakeNetwork
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class EarthquakeRepository(private val database: EarthquakeDatabase) {
    suspend fun refreshEarthquakes() {
        withContext(Dispatchers.IO) {
            val earthquakeList = EarthquakeNetwork.earthquakeService.getEarthquakeData()
            database.earthquakeDao.insertAllEarthquakes(earthquakeList.asDatabaseModel())
        }
    }

    val earthquakes: LiveData<List<EarthquakeModel>> = Transformations.map(database.earthquakeDao.getEarthquakes()) {
        it.asDomainModel()
    }
}