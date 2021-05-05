package com.example.team31.ui.employees

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class EmployeesViewModel(private val repo : Ansatt) : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "employees Fragment"
    }
    val text: LiveData<String> = _text
}