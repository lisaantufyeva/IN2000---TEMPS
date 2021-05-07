package com.example.team31.ui.overview.week_overview


import android.util.Log
import androidx.lifecycle.*

import com.example.team31.data.repositories.ForecastRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject


class OverviewViewModel @Inject constructor(
        private val repository: ForecastRepository): ViewModel() {

    private val _forecastListModelLiveData = MutableLiveData<List<RefinedForecast>>()
    val forecastList: LiveData<List<RefinedForecast>>
    get() = _forecastListModelLiveData

    // detailed fragment data
    private val selected = MutableLiveData<RefinedForecast>()
    fun select(item: RefinedForecast){
        selected.value = item
    }
    fun getSelected() = selected.value


    val errorMessage = MutableLiveData<String>()

     fun getForecastList(lat:String,lon: String){
         viewModelScope.launch {
             val result = repository.fetchLocationForecast(lat, lon)
             //val result = service.fetchLocationForecast(lat,lon)
             Log.d("result", "$result")

             withContext(Dispatchers.Main){
                 val forecastList = repository.createForecast(result)
                 println(forecastList)
                 _forecastListModelLiveData.value = forecastList
             }
        }
    }
}


fun checkLowStaffing(forecast: RefinedForecast, max: Double):Boolean{
    println("check low staffing:" +  forecast.temp.toDouble())
    return (forecast.temp.toDouble() >= max)
}
