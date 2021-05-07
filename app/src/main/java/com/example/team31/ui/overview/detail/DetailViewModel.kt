package com.example.team31.ui.overview.detail

import android.widget.Button
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.team31.ui.overview.week_overview.RefinedForecast
import kotlinx.coroutines.selects.select

class DetailViewModel(
    private val state: SavedStateHandle
) : ViewModel() {

    private val _selected = MutableLiveData<RefinedForecast>()
    val forecast: LiveData<RefinedForecast>
        get() = _selected

    var temp =forecast.value?.temp
    var date =forecast.value?.time
    var precipitation = forecast.value?.precipitation

}