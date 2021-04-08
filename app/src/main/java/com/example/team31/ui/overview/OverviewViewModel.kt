package com.example.team31.ui.overview

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class OverviewViewModel : ViewModel() {


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