package com.example.earthquake_app.model

import java.io.Serializable
import java.util.*

data class EarthquakeModel(
    val datetime: Date?,
    val depth: Double,
    val lng: Double,
    val src: String,
    val eqid: String,
    val magnitude: Double,
    val lat: Double,
    val isHighMagnitude: Boolean
) : Serializable