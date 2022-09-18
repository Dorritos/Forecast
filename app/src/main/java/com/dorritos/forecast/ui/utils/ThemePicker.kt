package com.dorritos.forecast.ui.utils

import android.annotation.SuppressLint
import android.content.Context
import androidx.core.content.ContextCompat
import com.dorritos.forecast.R
import com.dorritos.forecast.databinding.FragmentTodayBinding
import com.dorritos.forecast.remote.models.current.CurrentWeather

object ThemePicker {

    @SuppressLint("ResourceAsColor")
    fun applyWeatherCharacter(currentWeather: CurrentWeather, context: Context, binding: FragmentTodayBinding) {
        val temp = currentWeather.main.temp.toInt()
        val characterImage = binding.imageViewWeatherIcon
        val detailsBackground = binding.constraintLayout2
        when (temp) {
            in 35..40 -> {
                detailsBackground.setBackgroundColor(
                    ContextCompat.getColor(
                        context,
                        R.color.temp_40
                    )
                )
                characterImage.setImageResource(R.drawable.ic_temp_40)
            }
            in 30..35 -> {
                detailsBackground.setBackgroundColor(
                    ContextCompat.getColor(
                        context,
                        R.color.temp_35
                    )
                )
                characterImage.setImageResource(R.drawable.ic_temp_35)
            }
            in 25..30 -> {
                detailsBackground.setBackgroundColor(
                    ContextCompat.getColor(
                        context,
                        R.color.temp_30
                    )
                )
                characterImage.setImageResource(R.drawable.ic_temp_30)
            }
            in 20..25 -> {
                detailsBackground.setBackgroundColor(
                    ContextCompat.getColor(
                        context,
                        R.color.temp_25
                    )
                )
                characterImage.setImageResource(R.drawable.ic_temp_25)
            }
            in 15..20 -> {
                detailsBackground.setBackgroundColor(
                    ContextCompat.getColor(
                        context,
                        R.color.temp_20
                    )
                )
                characterImage.setImageResource(R.drawable.ic_temp_20)
            }
            in 10..15 -> {
                detailsBackground.setBackgroundColor(
                    ContextCompat.getColor(
                        context,
                        R.color.temp_15
                    )
                )
                characterImage.setImageResource(R.drawable.ic_temp_15)
            }
            in 5..10 -> {
                detailsBackground.setBackgroundColor(
                    ContextCompat.getColor(
                        context,
                        R.color.temp_10
                    )
                )
                characterImage.setImageResource(R.drawable.ic_temp_10)
            }
            in 0..5 -> {
                detailsBackground.setBackgroundColor(
                    ContextCompat.getColor(
                        context,
                        R.color.temp_5
                    )
                )
                characterImage.setImageResource(R.drawable.ic_temp_5)
            }
            in -5..0 -> {
                detailsBackground.setBackgroundColor(
                    ContextCompat.getColor(
                        context,
                        R.color.temp_0
                    )
                )
                characterImage.setImageResource(R.drawable.ic_temp_0)
            }
            in -1 downTo -5 -> {
                detailsBackground.setBackgroundColor(
                    ContextCompat.getColor(
                        context,
                        R.color.temp_minus_5
                    )
                )
                characterImage.setImageResource(R.drawable.ic_temp_minus_5)
            }
            in -5 downTo -10 -> {
                detailsBackground.setBackgroundColor(
                    ContextCompat.getColor(
                        context,
                        R.color.temp_minus_10
                    )
                )
                characterImage.setImageResource(R.drawable.ic_temp_minus_10)
            }
            in -10 downTo -15 -> {
                detailsBackground.setBackgroundColor(
                    ContextCompat.getColor(
                        context,
                        R.color.temp_minus_15
                    )
                )
                characterImage.setImageResource(R.drawable.ic_temp_minus_15)
            }
            in -15 downTo -20 -> {
                detailsBackground.setBackgroundColor(
                    ContextCompat.getColor(
                        context,
                        R.color.temp_minus_20
                    )
                )
                characterImage.setImageResource(R.drawable.ic_temp_minus_20)
            }
            in -20 downTo -25 -> {
                detailsBackground.setBackgroundColor(
                    ContextCompat.getColor(
                        context,
                        R.color.temp_minus_25
                    )
                )
                characterImage.setImageResource(R.drawable.ic_temp_minus_25)
            }
            in -25 downTo -30 -> {
                detailsBackground.setBackgroundColor(
                    ContextCompat.getColor(
                        context,
                        R.color.temp_minus_30
                    )
                )
                characterImage.setImageResource(R.drawable.ic_temp_minus_30)
            }
            in -30 downTo -35 -> {
                detailsBackground.setBackgroundColor(
                    ContextCompat.getColor(
                        context,
                        R.color.temp_minus_35
                    )
                )
                characterImage.setImageResource(R.drawable.ic_temp_minus_35)
            }
            else -> characterImage.setImageResource(R.drawable.ic_temp_0)
        }
    }
}