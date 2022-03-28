package com.dorritos.forecast.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.dorritos.forecast.remote.interfaces.WeatherRepositoryImpl
import com.dorritos.forecast.remote.models.current.DailyWeather

class DailyWeatherViewModel : ViewModel() {

    private val repository = WeatherRepositoryImpl()

    fun getDailyWeather(lang: String): LiveData<DailyWeather> {
        return repository.getDailyWeather(lang)
    }
}