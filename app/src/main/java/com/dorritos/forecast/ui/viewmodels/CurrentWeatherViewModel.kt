package com.dorritos.forecast.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dorritos.forecast.LocationHelper
import com.dorritos.forecast.remote.interfaces.WeatherServiceImpl
import com.dorritos.forecast.remote.models.current.CurrentWeather
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import org.koin.java.KoinJavaComponent.inject

class CurrentWeatherViewModel(private val locationHelper: LocationHelper) : ViewModel() {

    val currentWeatherFlow = MutableStateFlow<CurrentWeather?>(null)

    /*fun getCurrentWeather(lang: String) {
        viewModelScope.launch(Dispatchers.IO) {
            currentWeatherFlow.emit(locationHelper.getLocation(usersLocationLD.value)
                ?.let { repository.getCurrentWeather(lang, it) })
        }
    }*/

    fun getWeatherByCity(city: String?) {
        city?.let {
            val loc = locationHelper.getLocation(it)
            viewModelScope.launch {
                val weather = getCurrentWeather("ru", loc)
                currentWeatherFlow.emit(weather)
            }
        }
    }


}