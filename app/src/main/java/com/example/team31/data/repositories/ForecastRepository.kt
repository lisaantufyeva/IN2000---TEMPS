package com.example.team31.data.repositories

import android.annotation.SuppressLint
import com.example.team31.data.api.ForecastDto
import com.example.team31.data.api.LocationForecastApi
import com.example.team31.ui.overview.week_overview.Forecast
import com.example.team31.ui.overview.week_overview.RefinedForecast
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject


class ForecastRepository @Inject constructor(
    private val locationForecastApi: LocationForecastApi) {

    suspend fun fetchLocationForecast(lat: String, lon: String): ForecastDto {
        return locationForecastApi.fetchLocationForecast(lat, lon)
    }

    fun createForecast(result: ForecastDto): List<RefinedForecast>{
        val list = mutableListOf<Forecast>()
        for (i in result.properties.timeseries){
            val time = parseDate(i.time)
            val temp = i.data.instant?.details?.air_temperature
            val symbol = i.data.next_6_hours?.summary?.symbol_code
            val precipitation = i.data.next_6_hours?.details?.precipitation_amount
            val forecast = Forecast(time!!, temp.toString(), symbol, precipitation.toString())
            list.addAll(listOf(forecast))
        }
        return filterForecastList(list).map { forecast ->  RefinedForecast(formatDate(forecast.time), forecast.temp, forecast.symbol, forecast.precipitation) }
    }
    private fun filterForecastList(list: MutableList<Forecast>):List<Forecast>{
        val filteredList = list.filter { it.time.hours == 12 }
        val first = list.first()
        return if (first.time.hours > 12)
            listOf(list.first()) + filteredList
        else filteredList
    }

    @SuppressLint("SimpleDateFormat")

// takes in time:Date object and returns formatted date:String
    fun formatDate(time: Date?): String {
        val parser = SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy")
        val formatter = SimpleDateFormat("EEEE dd. MMMM", Locale("no", "NO"))

        return formatter.format(parser.parse(time.toString())!!)
    }

    //takes in time:String (from API) and returns time:Date
    @SuppressLint("SimpleDateFormat")
    fun parseDate(time: String?): Date? {
        val parser = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'")
        return parser.parse(time!!)
    }
}
