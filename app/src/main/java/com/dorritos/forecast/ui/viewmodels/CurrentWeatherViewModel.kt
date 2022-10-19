package com.dorritos.forecast.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dorritos.forecast.LocationHelper
import com.dorritos.forecast.remote.interfaces.WeatherService
import com.dorritos.forecast.remote.models.current.CurrentWeather
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class CurrentWeatherViewModel(
    private val locationHelper: LocationHelper,
    private val weatherService: WeatherService
) : ViewModel() {

    val currentWeatherFlow = MutableStateFlow<CurrentWeather?>(null)

    fun getWeatherByCity(city: String?) {
        city?.let {
            val loc = locationHelper.getLocation(it)
            viewModelScope.launch(Dispatchers.IO) {
                val weather = weatherService.getCurrentWeather("ru", loc)
                currentWeatherFlow.emit(weather)
            }
        }
    }
}