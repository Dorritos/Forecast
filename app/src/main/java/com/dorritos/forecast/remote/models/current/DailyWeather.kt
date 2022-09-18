package com.dorritos.forecast.remote.models.current

data class DailyWeather(
    val daily: List<Daily>,
    val lat: Double,
    val lon: Double,
    val timezone: String,
    val timezone_offset: Int
)