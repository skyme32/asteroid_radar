package com.udacity.asteroidradar.api

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import com.udacity.asteroidradar.utils.Constants.BASE_URL
import com.udacity.asteroidradar.utils.Constants.BASE_URL_APOD
import com.udacity.asteroidradar.utils.Constants.BASE_URL_FEED
import com.udacity.asteroidradar.PictureOfDay
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import java.util.concurrent.TimeUnit

class ApiService {

    /**
     * A retrofit service to fetch a devbyte playlist.
     * lynk of information: https://www.kinisoftware.com/conectar-lambda-de-skill-a-api-externa/
     */
    interface AsteroidService {
        @GET(BASE_URL_FEED)
        suspend fun getAsteroid(
            @Query("api_key") api_key: String
        ): String

        @GET(BASE_URL_APOD)
        suspend fun getPictureOfTheDay(
            @Query("api_key") key: String
        ): PictureOfDay
    }


    /**
     * Main entry point for network access. Call like 'Network.asteroidService.getAsteroid(API_KEY)'
     * When don't exist internet, have an erro, but i don't know how controll that
     */
    object Network {
        private val okHttpClient: OkHttpClient by lazy {
            OkHttpClient.Builder()
                .connectTimeout(5, TimeUnit.SECONDS)
                .readTimeout(20, TimeUnit.SECONDS)
                .build()
        }

        private val moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()

        /**
         * Configure retrofit to parse JSON and use coroutines
         */
        private val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(ScalarsConverterFactory.create())
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build()

        val asteroidService: AsteroidService = retrofit.create(AsteroidService::class.java)
    }
}


