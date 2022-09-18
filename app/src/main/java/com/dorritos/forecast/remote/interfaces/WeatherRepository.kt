package com.dorritos.forecast.remote.interfaces

import androidx.lifecycle.LiveData
import com.dorritos.forecast.LatLon
import com.dorritos.forecast.remote.models.current.CurrentWeather
import com.dorritos.forecast.remote.models.current.DailyWeather

interface WeatherRepository {
     suspend fun getCurrentWeather(lang: String, latLon: LatLon): CurrentWeather
     suspend fun getDailyWeather(lang: String, latLon: LatLon): DailyWeather
}