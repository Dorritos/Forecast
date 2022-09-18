package com.dorritos.forecast

import android.content.Context
import com.dorritos.forecast.ui.viewmodels.CurrentWeatherViewModel
import com.dorritos.forecast.ui.viewmodels.DailyWeatherViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

private const val prefsName = "appPrefs"

val appModule = module {

    single { androidContext().getSharedPreferences(prefsName, Context.MODE_PRIVATE) }

    factory { LocationHelper(get())}

    viewModel { CurrentWeatherViewModel(get()) }
    viewModel { DailyWeatherViewModel(get()) }

}