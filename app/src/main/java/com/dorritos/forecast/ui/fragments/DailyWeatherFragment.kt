package com.dorritos.forecast.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.ConfigurationCompat
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.dorritos.forecast.databinding.FragmentTendaysBinding
import com.dorritos.forecast.ui.adapters.DailyWeatherAdapter
import com.dorritos.forecast.ui.adapters.decorators.SimpleDecorator
import com.dorritos.forecast.ui.viewmodels.DailyWeatherViewModel

class DailyWeatherFragment : BaseFragment() {

    private var _binding: FragmentTendaysBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentTendaysBinding.inflate(inflater, container, false)
        val root: View = binding.root
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val recycler = binding.recyclerViewDailyForecast
        recycler.layoutManager = LinearLayoutManager(requireContext())

        recycler.addItemDecoration(SimpleDecorator(requireContext()))

        val DailyWeatherViewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(
            DailyWeatherViewModel::class.java)

        val lang = getLanguage()

        DailyWeatherViewModel.getDailyWeather(lang).observe(viewLifecycleOwner) { dailyWeather ->
            dailyWeather?.let {
                val adapter = DailyWeatherAdapter(dailyWeather.daily)
                recycler.adapter = adapter
            }
        }
    }

    private fun getLanguage(): String {
        val currentLocale = ConfigurationCompat.getLocales(resources.configuration)[0]
        return currentLocale.language
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}