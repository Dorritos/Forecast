package com.dorritos.forecast.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dorritos.forecast.LatLon
import com.dorritos.forecast.LocationHelper
import com.dorritos.forecast.remote.interfaces.WeatherService
import com.dorritos.forecast.remote.interfaces.WeatherServiceImpl
import com.dorritos.forecast.remote.models.current.DailyWeather
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class DailyWeatherViewModel(
    private val locationHelper: LocationHelper,
    private val service: WeatherService
) : ViewModel() {

    val dailyWeatherFlow = MutableStateFlow<DailyWeather?>(null)

    fun getDailyWeather(lang: String) {
        viewModelScope.launch(Dispatchers.IO) {
            dailyWeatherFlow.emit(service.getDailyWeather(lang, LatLon(23.423, 23.342)))
        }
    }
}