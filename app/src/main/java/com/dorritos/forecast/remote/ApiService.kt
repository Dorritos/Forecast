package com.dorritos.forecast.remote

import com.dorritos.forecast.remote.models.current.CurrentWeather
import com.dorritos.forecast.remote.models.current.DailyWeather
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    companion object {
        const val CURRENT_WEATHER = "/data/2.5/weather"
        const val DAILY_WEATHER = "/data/2.5/onecall"
    }

    @GET(CURRENT_WEATHER)
    suspend fun getCurrentWeather(
        @Query("lat") lat: String,
        @Query("lon") lon: String,
        @Query("appid") apiKey: String,
        @Query("units") units: String,
        @Query("lang") lang: String
    ): CurrentWeather

    @GET(DAILY_WEATHER)
    suspend fun getDailyWeather(
        @Query("lat") lat: String,
        @Query("lon") lon: String,
        @Query("exclude") exclude: String,
        @Query("lang") lang: String,
        @Query("units") units: String,
        @Query("appid") apiKey: String
    ): DailyWeather
}