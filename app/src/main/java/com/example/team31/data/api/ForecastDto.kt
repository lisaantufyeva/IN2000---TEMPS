package com.example.team31.data.api

data class ForecastDto(
    val properties: Properties,
    val timeseries: List<Timeseries>,
    val data: Data,
    val next_6_hours: Next_6_hours?,
    val instant: Instant?,
    val details: Details?,
    val summery: Summary?,
    val precipitation_amount: Precipitation?
)

data class Properties(val timeseries: List <Timeseries>)
data class Timeseries(val time: String, val data: Data)
data class Data(val instant: Instant?, val next_6_hours: Next_6_hours?)
data class Instant(val details: Details)
data class Details(val air_temperature: Number)

data class Next_6_hours(val summary: Summary?, val details: Precipitation)

data class Summary(val symbol_code: String?)

data class Precipitation(val precipitation_amount: Number?)

