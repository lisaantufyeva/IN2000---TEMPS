package com.example.team31.ui.overview

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class OverviewViewModel : ViewModel() {


    private val _text = MutableLiveData<String>().apply {
        value = "Overview fragment"
    }
    val text: LiveData<String> = _text
}