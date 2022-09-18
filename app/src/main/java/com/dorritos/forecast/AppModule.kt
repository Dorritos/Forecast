package com.dorritos.forecast

import android.content.Context
import com.dorritos.forecast.remote.ApiService
import com.dorritos.forecast.remote.interfaces.WeatherService
import com.dorritos.forecast.remote.interfaces.WeatherServiceImpl
import com.dorritos.forecast.ui.viewmodels.CurrentWeatherViewModel
import com.dorritos.forecast.ui.viewmodels.DailyWeatherViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val prefsName = "appPrefs"
private const val BASE_URL: String = "https://api.openweathermap.org"

val appModule = module {

    single { androidContext().getSharedPreferences(prefsName, Context.MODE_PRIVATE) }

    single { LocationHelper(androidContext())}

    single { WeatherServiceImpl(get()) }

    single { Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    }

    single { get<Retrofit>().create(ApiService::class.java) }

    viewModel { CurrentWeatherViewModel(get()) }
    viewModel { DailyWeatherViewModel(get(), get<WeatherServiceImpl>() as WeatherService) }

}