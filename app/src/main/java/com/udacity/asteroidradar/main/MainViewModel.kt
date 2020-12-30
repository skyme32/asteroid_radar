package com.udacity.asteroidradar.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.udacity.asteroidradar.Asteroid

class MainViewModel : ViewModel() {

    private val list = mutableListOf(
        Asteroid(1, "Moliesus", "Mo mo", 1.0,1.0,8.0, 12.0, false),
        Asteroid(1, "Moliesus", "Mo mo", 1.0,1.0,8.0, 12.0, true),
        Asteroid(1, "Moliesus", "Mo mo", 1.0,1.0,8.0, 12.0, false),
        Asteroid(1, "Moliesus", "Mo mo", 1.0,1.0,8.0, 12.0, true),
        Asteroid(1, "Moliesus", "Mo mo", 1.0,1.0,8.0, 12.0, false),
        Asteroid(1, "Moliesus", "Mo mo", 1.0,1.0,8.0, 12.0, false),
        Asteroid(1, "Moliesus", "Mo mo", 1.0,1.0,8.0, 12.0, false),
        Asteroid(1, "Moliesus", "Mo mo", 1.0,1.0,8.0, 12.0, true),
        Asteroid(1, "Asteroidus", "Hi hi", 7.0,2.0,2.0, 4.0, true)
    )

    private val _asteroidList = MutableLiveData<List<Asteroid>>(list)
    val asteroidList: LiveData<List<Asteroid>>
        get() = _asteroidList


    private val _navigateToDetailAsteroid = MutableLiveData<Asteroid>()
    val navigateToDetailAsteroid: LiveData<Asteroid>
        get() = _navigateToDetailAsteroid


    fun onAsteroidClicked(asteroid: Asteroid) {
        _navigateToDetailAsteroid.value = asteroid
    }

    fun onAsteroidNavigated() {
        _navigateToDetailAsteroid.value = null
    }
}