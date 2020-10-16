package com.example.earthquake_app.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.earthquake_app.model.EarthquakeModel
import java.util.*

@Entity
data class DatabaseEarthquake constructor(
    @PrimaryKey
    val eqid: String,
    val datetime: Date?,
    val depth: Double,
    val lng: Double,
    val src: String,
    val magnitude: Double,
    val lat: Double
)

fun List<DatabaseEarthquake>.asDomainModel(): List<EarthquakeModel> {
    return map {
        EarthquakeModel(
            datetime = it.datetime,
            depth = it.depth,
            lng = it.lng,
            src = it.src,
            eqid = it.eqid,
            magnitude = it.magnitude,
            lat = it.lat,
            isHighMagnitude = it.magnitude >= 8.00
        )
    }
}