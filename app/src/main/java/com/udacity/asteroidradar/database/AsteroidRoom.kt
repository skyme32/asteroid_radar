package com.udacity.asteroidradar.database

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface AsteroidDao {

    @Query("SELECT * FROM asteroids")
    fun getAsteroids(): LiveData<List<DatabaseAsteroidEntities>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(asteroids: List<DatabaseAsteroidEntities>)
}

@Database(entities = [DatabaseAsteroidEntities::class], version = 1, exportSchema = false)
abstract class AsteroidDatabase : RoomDatabase() {
    abstract val asteroidDao: AsteroidDao
}

private lateinit var INSTANCE: AsteroidDatabase

fun getDatabase(context: Context): AsteroidDatabase {
    synchronized(AsteroidDatabase::class.java) {
        if (!::INSTANCE.isInitialized) {
            INSTANCE = Room.databaseBuilder(
                context.applicationContext,
                AsteroidDatabase::class.java,
                "videos"
            ).build()
        }
    }
    return INSTANCE
}