package com.example.team31.data.api

import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface LocationForecastApi {

    @GET("weatherapi/locationforecast/2.0/compact")
    // runs on specified thread
    suspend fun fetchLocationForecast(
        @Query("lat") lat: String,
        @Query("lon") lon: String
    ): ForecastDto

    companion object {
        operator fun invoke(): LocationForecastApi {
            return Retrofit.Builder()
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl("https://in2000-apiproxy.ifi.uio.no/")
                    .build()
                    .create(LocationForecastApi::class.java)
        }
    }
}

