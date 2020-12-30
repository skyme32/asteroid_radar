package com.udacity.asteroidradar.api

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.udacity.asteroidradar.Constants.API_KEY
import com.udacity.asteroidradar.Constants.BASE_URL
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query
import java.util.concurrent.TimeUnit

class ApiService {

    /**
     * A retrofit service to fetch a devbyte playlist.
     * lynk of information: https://www.kinisoftware.com/conectar-lambda-de-skill-a-api-externa/
     */
    interface AsteroidService {

        @Headers("Accept-Version: v1", "Authorization: Client-ID $API_KEY")
        @GET("neo/rest/v1/feed")
        suspend fun getAsteroid(
            @Query("api_key") api_key: String
        ): String
    }

    /**
     * Main entry point for network access. Call like 'Network.asteroidService.getAsteroid(API_KEY)'
     */
    object Network {

        private val okHttpClient = OkHttpClient.Builder()
            .connectTimeout(20, TimeUnit.SECONDS)
            .writeTimeout(20, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .build()


        // Configure retrofit to parse JSON and use coroutines
        private val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(ScalarsConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            //.client(okHttpClient)
            .build()

        val asteroidService: AsteroidService = retrofit.create(AsteroidService::class.java)
    }
}

