package com.dorritos.forecast.remote.interfaces

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.dorritos.forecast.BuildConfig
import com.dorritos.forecast.remote.RetrofitService
import com.dorritos.forecast.remote.models.current.CurrentWeather
import com.dorritos.forecast.remote.models.current.DailyWeather
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class WeatherRepositoryImpl : WeatherRepository {

    private val api = RetrofitService.getInstance().getApi()

    override fun getCurrentWeather(lang: String): LiveData<CurrentWeather> {
        val data = MutableLiveData<CurrentWeather>()
       CoroutineScope(Dispatchers.IO).launch {
            val response = api.getCurrentWeather("62.0397", "129.7422", BuildConfig.API_KEY, "metric", lang)
            data.postValue(response)
        }
        return data
    }

    override fun getDailyWeather(lang: String): LiveData<DailyWeather> {
        val data = MutableLiveData<DailyWeather>()
        CoroutineScope(Dispatchers.IO).launch {
            val response = api.getDailyWeather("54.2000", "45.1745", "hourly,current,minutely,alerts", lang, "metric", BuildConfig.API_KEY)
            data.postValue(response)
        }
        return data
    }
}
