package com.dorritos.forecast.remote.models.current

data class Main(
    val feels_like: Double,
    val humidity: Int,
    val pressure: Int,
    val temp: Double,
    val temp_max: Double,
    val temp_min: Double
) {
    fun getCelcium(temp: Double): String {
        return "${temp.toInt()}°C"
    }
}