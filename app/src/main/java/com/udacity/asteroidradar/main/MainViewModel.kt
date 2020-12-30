package com.udacity.asteroidradar.main

import android.util.Log
import androidx.lifecycle.*
import androidx.work.impl.constraints.controllers.NetworkUnmeteredController
import com.udacity.asteroidradar.Asteroid
import com.udacity.asteroidradar.Constants
import com.udacity.asteroidradar.Constants.API_KEY
import com.udacity.asteroidradar.api.ApiService
import com.udacity.asteroidradar.api.parseAsteroidsJsonResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.json.JSONObject
import timber.log.Timber

class MainViewModel : ViewModel() {

    private val list = mutableListOf(
        Asteroid(1, "Moliesus", "Mo mo", 1.0, 1.0, 8.0, 12.0, false),
        Asteroid(1, "Moliesus", "Mo mo", 1.0, 1.0, 8.0, 12.0, true),
        Asteroid(1, "Moliesus", "Mo mo", 1.0, 1.0, 8.0, 12.0, false),
        Asteroid(1, "Moliesus", "Mo mo", 1.0, 1.0, 8.0, 12.0, true),
        Asteroid(1, "Moliesus", "Mo mo", 1.0, 1.0, 8.0, 12.0, false),
        Asteroid(1, "Moliesus", "Mo mo", 1.0, 1.0, 8.0, 12.0, false),
        Asteroid(1, "Moliesus", "Mo mo", 1.0, 1.0, 8.0, 12.0, false),
        Asteroid(1, "Moliesus", "Mo mo", 1.0, 1.0, 8.0, 12.0, true),
        Asteroid(1, "Asteroidus", "Hi hi", 7.0, 2.0, 2.0, 4.0, true)
    )

    private val _asteroidList = MutableLiveData<List<Asteroid>>(list)
    val asteroidList: LiveData<List<Asteroid>>
        get() = _asteroidList


    private val _navigateToDetailAsteroid = MutableLiveData<Asteroid>()
    val navigateToDetailAsteroid: LiveData<Asteroid>
        get() = _navigateToDetailAsteroid

    init {
        viewModelScope.launch {
            refreshAsteroids()
        }
    }

    fun onAsteroidClicked(asteroid: Asteroid) {
        _navigateToDetailAsteroid.value = asteroid
    }

    fun onAsteroidNavigated() {
        _navigateToDetailAsteroid.value = null
    }

    private suspend fun refreshAsteroids() {
        withContext(Dispatchers.IO) {
            try {
                val asteroids = ApiService.Network.asteroidService.getAsteroid(API_KEY)
                val result = parseAsteroidsJsonResult(JSONObject(asteroids))
                //database.videoDao.insertAll(*playlist.asDatabaseModel())
                Timber.i(result.toString())
            } catch (err: Exception) {
                //Timber.e(err.printStackTrace().toString())
                err.printStackTrace()
            }
        }
    }
}