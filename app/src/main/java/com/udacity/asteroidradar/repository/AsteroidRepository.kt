package com.udacity.asteroidradar.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.udacity.asteroidradar.Asteroid
import com.udacity.asteroidradar.Constants.API_KEY
import com.udacity.asteroidradar.api.ApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import timber.log.Timber

/**
class AsteroidRepository(private val database: VideosDatabase) {

/**
 * A playlist of asteroids that can be shown on the screen.
*/
val asteroids: LiveData<List<Asteroid>> =
Transformations.map(database.videoDao.getVideos()) {
it.asDomainModel()
}

/**
 * Refresh the asteroids stored in the offline cache.
 * To actually load the asteroids for use, observe [asteroids]
*/
suspend fun refreshAsteroids() {
withContext(Dispatchers.IO) {
try {
val asteroids = ApiService.Network.asteroidService.getAsteroid(API_KEY)
//database.videoDao.insertAll(*playlist.asDatabaseModel())
Timber.i(asteroids)
} catch (err: Exception) {
Timber.e(err.printStackTrace().toString())
}
}
}


}
 **/
