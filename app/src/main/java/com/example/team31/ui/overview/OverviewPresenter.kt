package com.example.team31.ui.overview

import android.content.Context
import android.util.Log
import com.example.team31.data.api.ForecastDto
import com.example.team31.data.api.LocationForecastApi
import kotlinx.coroutines.*
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
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

    suspend fun getForecastList(): MutableList<Forecast> {
        var forecastList = mutableListOf<Forecast>()
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
            println(forecastList.size)
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
fun createForecast(result: ForecastDto): MutableList<Forecast>{
    val list = mutableListOf<Forecast>()
    for (i in result.properties.timeseries){
        val time = i.time
        val temp = i.data.instant.details.air_temperature
        val forecast = Forecast(time, temp.toString())
        list.addAll(listOf(forecast))
    }
    return list
}

fun filterDate(date: String){

}

data class Forecast(val time: String, val temp: String)