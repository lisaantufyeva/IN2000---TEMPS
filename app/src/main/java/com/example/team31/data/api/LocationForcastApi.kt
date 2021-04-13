package com.example.team31.data.api

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface LocationForecastApi{


    @GET("weatherapi/locationforecast/2.0/compact")

    // runs on specified thread
    suspend fun fetchLocationForecast(@Query("lat") lat: String, @Query("lon") lon :String): ForecastDto



}


data class ForecastDto(
    val properties: Properties,
    val timeseries: List<Timeseries>,
    val data: Data,
    val instant: Instant,
    val details: Details
)
data class Properties(val timeseries: List <Timeseries>)
data class Timeseries(val time: String, val data: Data)
data class Data(val instant: Instant)
data class Instant(val details: Details)
data class Details(val air_temperature: Number)

//data class Next_1_hours(val summary: Summary?)
//data class Summary(val symbol_code: String?)*/
