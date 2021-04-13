package com.example.team31.ui.overview

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class OverviewViewModel : ViewModel() {

    /*
    //private var viewModelJob = Job()
    //private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    val forecastLiveData = MutableLiveData<ForecastDto>()

    //var location: String = "lat=60&lon=11"
    fun getForecast(location: String){

        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.met.no")
            .build()

        val service: LocationForcastApi = retrofit.create(LocationForcastApi::class.java)

        viewModelScope.launch(){
            val result = service.fetchLocationForecast(location)
            Log.d("result", "$result")
            forecastLiveData.postValue(result)

        }
    }*/





    private val _text = MutableLiveData<String>().apply {
        value = "Overview fragment"
    }
    private val _date = MutableLiveData<String>().apply{
        value = "I dag 8.april"
    }
    private val _degrees = MutableLiveData<String>().apply {
        value = "25"
    }

    val text: LiveData<String> = _text
    val date: LiveData<String> = _date
    val degrees: LiveData<String> = _degrees

}

