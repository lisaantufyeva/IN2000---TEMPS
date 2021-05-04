package com.example.team31.ui.overview.detail

import android.widget.Button
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.team31.ui.overview.week_overview.RefinedForecast

class DetailViewModel : ViewModel() {

    private val _selected = MutableLiveData<RefinedForecast>()
    val forecast: LiveData<RefinedForecast>
        get() = _selected



}