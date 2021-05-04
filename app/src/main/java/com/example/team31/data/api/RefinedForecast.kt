package com.example.team31.ui.overview.week_overview

import android.os.Parcel
import android.os.Parcelable
import java.util.*


data class Forecast(val time: Date, val temp: String, val symbol: String?, val precipitation: String?)

data class RefinedForecast(val time: String, val temp: String, val symbol: String?, val precipitation: String?): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString(),
        parcel.readString()

    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(time)
        parcel.writeString(temp)
        parcel.writeString(symbol)
        parcel.writeString(precipitation)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<RefinedForecast> {
        override fun createFromParcel(parcel: Parcel): RefinedForecast {
            return RefinedForecast(parcel)
        }

        override fun newArray(size: Int): Array<RefinedForecast?> {
            return arrayOfNulls(size)
        }
    }
}
