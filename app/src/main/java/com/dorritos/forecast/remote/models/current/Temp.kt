package com.dorritos.forecast.remote.models.current

data class Temp(
    val day: Double,
    val eve: Double,
    val max: Double,
    val min: Double,
    val morn: Double,
    val night: Double
) {
    fun getDailyTempCelcium(temp: Double): String {
        return "${temp.toInt()}°C"
    }
}
