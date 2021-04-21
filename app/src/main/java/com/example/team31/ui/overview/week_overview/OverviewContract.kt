package com.example.team31.ui.overview.week_overview

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

