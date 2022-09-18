package com.dorritos.forecast.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.dorritos.forecast.databinding.FragmentTomorrowBinding
import com.dorritos.forecast.remote.models.current.DailyWeather
import com.dorritos.forecast.ui.viewmodels.DailyWeatherViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class TomorrowFragment : BaseFragment() {

    private var _binding: FragmentTomorrowBinding? = null
    private val binding get() = _binding!!
    private val dailyWeatherViewModel: DailyWeatherViewModel by viewModel()


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {

        _binding = FragmentTomorrowBinding.inflate(inflater, container, false)
        val root: View = binding.root
        return root
    }

    private fun initTempViews(dailyWeather: DailyWeather) {
        binding.apply {
            textViewTempMinValue.text = dailyWeather.daily.first().temp.getDailyTempCelcium(dailyWeather.daily.first().temp.max)
            textViewTempMaxValue.text = dailyWeather.daily.first().temp.getDailyTempCelcium(dailyWeather.daily.first().temp.min)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}