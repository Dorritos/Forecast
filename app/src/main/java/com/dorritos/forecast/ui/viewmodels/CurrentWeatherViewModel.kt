package com.dorritos.forecast.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dorritos.forecast.remote.interfaces.WeatherRepositoryImpl
import com.dorritos.forecast.remote.models.current.CurrentWeather
import com.dorritos.forecast.remote.models.current.Weather

class CurrentWeatherViewModel : ViewModel() {

    private val repository = WeatherRepositoryImpl()
    //private val currentWeather = repository.getCurrentWeather()

    fun getCurrentWeather(lang: String): LiveData<CurrentWeather> {
        return repository.getCurrentWeather(lang)
    }
}