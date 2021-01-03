package com.udacity.asteroidradar.utils

import retrofit2.http.GET
import retrofit2.http.Query

object Constants {
    const val API_QUERY_DATE_FORMAT = "yyyy-MM-dd"
    const val DEFAULT_END_DATE_DAYS = 7
    const val BASE_URL = "https://api.nasa.gov/"
    const val API_KEY = "iBhELDnQTcIdHTNteteURmvEFBJV69dLce02igDG"
    const val BASE_URL_FEED = "neo/rest/v1/feed"
    const val BASE_URL_APOD = "planetary/apod"
}