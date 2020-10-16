package com.example.earthquake_app.database

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.*
import java.util.*

@Dao
interface EarthquakeDao {

    @Query("select * from databaseearthquake")
    fun getEarthquakes(): LiveData<List<DatabaseEarthquake>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllEarthquakes(earthquakes: List<DatabaseEarthquake>)
}

@Database(entities = [DatabaseEarthquake::class], version = 1)
@TypeConverters(Converters::class)
abstract class EarthquakeDatabase: RoomDatabase() {
    abstract val earthquakeDao: EarthquakeDao
}

private lateinit var INSTANCE: EarthquakeDatabase

fun getDatabase(context: Context): EarthquakeDatabase {
    synchronized(EarthquakeDatabase::class.java) {
        if (!::INSTANCE.isInitialized) {
            INSTANCE = Room.databaseBuilder(context.applicationContext,
            EarthquakeDatabase::class.java,
            "earthquakes").build()
        }
        return INSTANCE
    }
}

class Converters {
    @TypeConverter
    fun fromTimestamp(value: Long?): Date? {
        return value?.let { Date(it) }
    }

    @TypeConverter
    fun dateToTimestamp(date: Date?): Long? {
        return date?.time?.toLong()
    }
}