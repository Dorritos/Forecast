package com.dorritos.forecast.remote

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class RetrofitService() {

    companion object {
        private var instance: RetrofitService? = null
        fun getInstance(): RetrofitService {
            if (instance == null) {
                instance = RetrofitService()
            }
            return instance!!
        }
    }

    private var retrofit: Retrofit
    private val BASE_URL: String = "https://api.openweathermap.org"

    fun getApi(): ApiService {
        return retrofit.create(ApiService::class.java)
    }

    init {
        retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}