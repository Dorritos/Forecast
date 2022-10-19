package com.dorritos.forecast.remote.interfaces

import com.dorritos.forecast.BuildConfig
import com.dorritos.forecast.LatLon
import com.dorritos.forecast.remote.ApiService
import com.dorritos.forecast.remote.models.current.CurrentWeather
import com.dorritos.forecast.remote.models.current.DailyWeather

class
 WeatherServiceImpl(private val api: ApiService) : WeatherService {

    private companion object {
        private const val SYSTEM = "metric"
        private const val EXCLUDE = "hourly,current,minutely,alerts"
    }


    override suspend fun getCurrentWeather(lang: String, latLon: LatLon): CurrentWeather {
        return api.getCurrentWeather(latLon.lat.toString(), latLon.lon.toString(), BuildConfig.API_KEY, SYSTEM, lang)
    }

    override suspend fun getDailyWeather(lang: String, latLon: LatLon): DailyWeather {
        return api.getDailyWeather(latLon.lat.toString(), latLon.lon.toString(), EXCLUDE, lang, SYSTEM, BuildConfig.API_KEY)
    }
}
