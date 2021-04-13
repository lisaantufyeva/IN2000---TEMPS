package com.example.team31.ui.overview

import com.example.team31.data.api.ForecastDto
import kotlinx.coroutines.CompletableJob
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import retrofit2.Response
import java.net.ResponseCache
import kotlin.coroutines.CoroutineContext

interface OverviewContract {


    interface View {
        fun onDestroy()

    }

    interface Presenter<T> {

        fun getForecastList(OnFinishedListener: Model.OnFinishedListener?)

    }

    interface Model{

        interface OnFinishedListener {

            fun onFinished(string: String?)
        }


    }
    
}

