package com.dorritos.forecast.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dorritos.forecast.LocationHelper
import com.dorritos.forecast.remote.interfaces.WeatherRepositoryImpl
import com.dorritos.forecast.remote.models.current.CurrentWeather
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class CurrentWeatherViewModel(private val locationHelper: LocationHelper) : ViewModel() {

    val currentWeatherFlow = MutableStateFlow<CurrentWeather?>(null)
    private val usersLocationLD = MutableStateFlow("Moscow")
    private val repository = WeatherRepositoryImpl()

    fun getCurrentWeather(lang: String) {
        viewModelScope.launch(Dispatchers.IO) {
            currentWeatherFlow.emit(locationHelper.getLocation(usersLocationLD.value)
                ?.let { repository.getCurrentWeather(lang, it) })
        }
    }
}