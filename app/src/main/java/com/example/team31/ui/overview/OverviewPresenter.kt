package com.example.team31.ui.overview

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import com.example.team31.data.api.ForecastDto
import com.example.team31.data.api.LocationForecastApi
import kotlinx.coroutines.*
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.text.SimpleDateFormat
import java.util.*
import kotlin.coroutines.CoroutineContext

class OverviewPresenter(
        private var overviewView: Context?,
        private var model: OverviewContract.Model): OverviewContract.Presenter<Any?>, CoroutineScope,
        OverviewContract.Model.OnFinishedListener {


    private var job = Job()
    override val coroutineContext: CoroutineContext = job + Dispatchers.Main

    override fun getForecastList(OnFinishedListener: OverviewContract.Model.OnFinishedListener?) {
        TODO("Not yet implemented")
    }

    fun onDestroy() {
        overviewView = null
    }

    suspend fun getForecastList(): List<RefinedForecast> {
        var forecastList: List<RefinedForecast>
        val retrofit = Retrofit.Builder()
            .baseUrl("https://in2000-apiproxy.ifi.uio.no/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        println("test 1")

        val service: LocationForecastApi = retrofit.create(LocationForecastApi::class.java)

        withContext(Dispatchers.Main) {
            val result = service.fetchLocationForecast("59.9","10.75")
            Log.d("result", "$result")
            forecastList = (createForecast(result))
            println(forecastList)
            }
        return forecastList
        }

    override fun onFinished(string: String?) {
        TODO("Not yet implemented")
    }
    /*
    override fun cleanup() {
    job.cancel()
    }*/
}
fun createForecast(result: ForecastDto): List<RefinedForecast>{
    val list = mutableListOf<Forecast>()
    for (i in result.properties.timeseries){
        val time = parseDate(i.time)
        val temp = i.data.instant?.details?.air_temperature
        val symbol = i.data.next_6_hours?.summary?.symbol_code
        val forecast = Forecast(time, temp.toString(), symbol)
        list.addAll(listOf(forecast))
    }
    return filterForecastList(list).map { forecast ->  RefinedForecast(formatDate(forecast.time), forecast.temp, forecast.symbol)}
}


//returns a list of Forecast objects

fun filterForecastList(list: MutableList<Forecast>):List<Forecast>{
    val filteredList = list.filter { it.time.hours == 12 }
    val first = list.first()

    return if (first.time.hours > 12)
        listOf(list.first()) + filteredList
    else filteredList
}

@SuppressLint("SimpleDateFormat")

// takes in time:Date object and returns date:String
fun formatDate(time:Date): String {
    val parser = SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy")
    val formatter = SimpleDateFormat("EEEE dd. MMMM")

    return formatter.format(parser.parse(time.toString()))
}

//takes time: String(from API) and returns time:Date
@SuppressLint("SimpleDateFormat")
fun parseDate(time: String): Date {
    val parser = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'")
    return parser.parse(time)
}


data class Forecast(val time: Date, val temp: String, val symbol: String?)

data class RefinedForecast(val time: String, val temp: String, val symbol: String?)