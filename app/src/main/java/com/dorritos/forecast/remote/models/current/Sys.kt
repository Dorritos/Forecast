package com.dorritos.forecast.remote.models.current

data class Sys(
    val country: String,
    val id: Int,
    val message: Double,
    val sunrise: Int,
    val sunset: Int,
    val type: Int
) {
    fun getCountryName(): String {
        return when(country) {
            "RU" -> "Russia"
            else -> country
        }
    }
}