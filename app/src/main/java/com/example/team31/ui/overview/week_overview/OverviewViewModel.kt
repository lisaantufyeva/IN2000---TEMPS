package com.example.team31.ui.overview.week_overview

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.team31.data.api.ForecastDto
import com.example.team31.data.api.LocationForecastApi
import com.example.team31.data.repositories.ForecastRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.text.SimpleDateFormat
import java.util.*
import kotlin.coroutines.CoroutineContext

class OverviewViewModel: ViewModel() {

    //private var job = Job()
     //val coroutineContext: CoroutineContext = job + Dispatchers.Main

    private val _forecastListModelLiveData = MutableLiveData<List<RefinedForecast>>()
    val forecastList: LiveData<List<RefinedForecast>>
    get() = _forecastListModelLiveData

    val errorMessage = MutableLiveData<String>()

     fun getForecastList(lat:String,lon: String){

         val retrofit = Retrofit.Builder()
             .baseUrl("https://in2000-apiproxy.ifi.uio.no/")
             .addConverterFactory(GsonConverterFactory.create())
             .build()

         val service: LocationForecastApi = retrofit.create(LocationForecastApi::class.java)

         viewModelScope.launch {
             println("test3")
             val result = service.fetchLocationForecast(lat,lon)
             Log.d("result", "$result")

             withContext(Dispatchers.Main){
                 val forecastList = (createForecast(result))
                 println(forecastList)
                 _forecastListModelLiveData.value = forecastList
             }

        }
    }


}

fun createForecast(result: ForecastDto): List<RefinedForecast>{
    val list = mutableListOf<Forecast>()
    for (i in result.properties.timeseries){
        val time = parseDate(i.time)
        val temp = i.data.instant?.details?.air_temperature
        val symbol = i.data.next_6_hours?.summary?.symbol_code
        val precipitation = i.data.next_6_hours?.details?.precipitation_amount
        val forecast = Forecast(time, temp.toString(), symbol, precipitation.toString())
        list.addAll(listOf(forecast))
    }
    return filterForecastList(list).map { forecast ->  RefinedForecast(formatDate(forecast.time), forecast.temp, forecast.symbol, forecast.precipitation) }
}
fun filterForecastList(list: MutableList<Forecast>):List<Forecast>{
    val filteredList = list.filter { it.time.hours == 12 }
    val first = list.first()

    return if (first.time.hours > 12)
        listOf(list.first()) + filteredList
    else filteredList
}

@SuppressLint("SimpleDateFormat")

// takes in time:Date object and returns date:String
fun formatDate(time: Date): String {
    val parser = SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy")
    val formatter = SimpleDateFormat("EEEE dd. MMMM", Locale("no", "NO"))

    return formatter.format(parser.parse(time.toString()))
}

//takes time: String(from API) and returns time:Date
@SuppressLint("SimpleDateFormat")
fun parseDate(time: String): Date {
    val parser = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'")
    return parser.parse(time)
}

fun checkLowStaffing(forecast: RefinedForecast, max: Double):Boolean{
    println("check low staffing:" +  forecast.temp.toDouble())
    return (forecast.temp.toDouble() >= max)
}
