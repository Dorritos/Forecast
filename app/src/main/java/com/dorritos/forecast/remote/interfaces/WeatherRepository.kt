package com.dorritos.forecast.remote.interfaces

import androidx.lifecycle.LiveData
import com.dorritos.forecast.remote.models.current.CurrentWeather
import com.dorritos.forecast.remote.models.current.DailyWeather

interface WeatherRepository {
     fun getCurrentWeather(lang: String) : LiveData<CurrentWeather>
     fun getDailyWeather(lang: String) : LiveData<DailyWeather>
}