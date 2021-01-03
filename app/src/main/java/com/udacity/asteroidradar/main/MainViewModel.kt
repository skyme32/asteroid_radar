package com.udacity.asteroidradar.main

import android.app.Application
import androidx.lifecycle.*
import com.udacity.asteroidradar.Asteroid
import com.udacity.asteroidradar.utils.Constants.API_KEY
import com.udacity.asteroidradar.PictureOfDay
import com.udacity.asteroidradar.api.ApiService
import com.udacity.asteroidradar.database.getDatabase
import com.udacity.asteroidradar.repository.AsteroidRepository
import com.udacity.asteroidradar.utils.FilterAsteroid
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import timber.log.Timber


class MainViewModel(application: Application) : AndroidViewModel(application) {

    /**
     * Dummy data
     */
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

    private val database = getDatabase(application)
    private val asteroidRepository = AsteroidRepository(database)

    private val _pictureofDay = MutableLiveData<PictureOfDay>()
    val pictureofDay: LiveData<PictureOfDay>
        get() = _pictureofDay

    private val _navigateToDetailAsteroid = MutableLiveData<Asteroid>()
    val navigateToDetailAsteroid: LiveData<Asteroid>
        get() = _navigateToDetailAsteroid

    private var _filterAsteroid = MutableLiveData(FilterAsteroid.ALL)

    val playlist = Transformations.switchMap(_filterAsteroid) {
        when (it!!) {
            FilterAsteroid.WEEK -> asteroidRepository.weekAsteroids
            FilterAsteroid.TODAY -> asteroidRepository.todayAsteroids
            else -> asteroidRepository.allAsteroids
        }
    }

    init {
        viewModelScope.launch {
            asteroidRepository.refreshAsteroids()
            refreshPictureofDay()
        }
    }

    fun onAsteroidClicked(asteroid: Asteroid) {
        _navigateToDetailAsteroid.value = asteroid
    }

    fun onAsteroidNavigated() {
        _navigateToDetailAsteroid.value = null
    }

    fun onChangeFilter(filter: FilterAsteroid) {
        _filterAsteroid.postValue(filter)
    }

    /**
     * Factory for constructing MainViewModel with parameter
     */
    class Factory(val app: Application) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return MainViewModel(app) as T
            }
            throw IllegalArgumentException("Unable to construct viewmodel")
        }
    }


    /**
    private suspend fun refreshAsteroids() {
    withContext(Dispatchers.IO) {
    try {
    val asteroids = ApiService.Network.asteroidService.getAsteroid(API_KEY)
    val result = parseAsteroidsJsonResult(JSONObject(asteroids))
    _asteroidList.postValue(result)

    Timber.i("Works!!! ${result.size}")
    } catch (err: Exception) {
    Timber.e(err.printStackTrace().toString())
    }
    }
     **/


    private suspend fun refreshPictureofDay() {
        withContext(Dispatchers.IO) {
            try {
                //_pictureofDay.postValue(ApiService.Network.asteroidService.getPictureOfTheDay(API_KEY, LocalDate.of(2020,12, 28)))
                //_pictureofDay.postValue(ApiService.Network.asteroidService.getPictureOfTheDay(API_KEY, LocalDate.now()))
                _pictureofDay.postValue(
                    ApiService.Network.asteroidService.getPictureOfTheDay(
                        API_KEY
                    )
                )
            } catch (err: Exception) {
                Timber.e(err.printStackTrace().toString())
            }
        }
    }
}