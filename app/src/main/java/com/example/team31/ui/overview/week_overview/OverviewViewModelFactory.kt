package com.example.team31.ui.overview.week_overview

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.team31.data.repositories.ForecastRepository
import java.lang.IllegalArgumentException

class OverviewViewModelFactory constructor(private val repository: ForecastRepository): ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(OverviewViewModel::class.java)) {
            OverviewViewModel(this.repository) as T
        } else {
            throw IllegalArgumentException("ViewModel not found")
        }
    }
}