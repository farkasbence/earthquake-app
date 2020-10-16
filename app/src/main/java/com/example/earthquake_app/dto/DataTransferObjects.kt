package com.example.earthquake_app.dto

import com.example.earthquake_app.database.DatabaseEarthquake
import com.squareup.moshi.JsonClass
import java.text.SimpleDateFormat

@JsonClass(generateAdapter = true)
data class EarthquakeResponse(val earthquakes: List<Earthquake>)

@JsonClass(generateAdapter = true)
data class Earthquake(
    val datetime: String,
    val depth: Double,
    val lng: Double,
    val src: String,
    val eqid: String,
    val magnitude: Double,
    val lat: Double
)

fun EarthquakeResponse.asDatabaseModel(): List<DatabaseEarthquake> {
    return earthquakes.map {
        DatabaseEarthquake(
            datetime = SimpleDateFormat("yyyy-MM-dd' 'HH:mm:ss").parse(it.datetime),
            depth = it.depth,
            lng = it.lng,
            src = it.src,
            eqid = it.eqid,
            magnitude = it.magnitude,
            lat = it.lat
        )
    }
}