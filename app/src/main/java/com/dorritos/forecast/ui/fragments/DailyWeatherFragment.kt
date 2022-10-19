package com.dorritos.forecast.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.ConfigurationCompat
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.dorritos.forecast.databinding.FragmentDailyBinding
import com.dorritos.forecast.ui.adapters.DailyWeatherAdapter
import com.dorritos.forecast.ui.adapters.decorators.SimpleDecorator
import com.dorritos.forecast.ui.viewmodels.DailyWeatherViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class DailyWeatherFragment : BaseFragment() {

    private var _binding: FragmentDailyBinding? = null
    private val binding get() = _binding!!
    private val adapter: DailyWeatherAdapter by lazy { DailyWeatherAdapter(lifecycleScope) }
    private val dailyWeatherViewModel: DailyWeatherViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDailyBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val recycler = binding.recyclerViewDailyForecast
        recycler.layoutManager = LinearLayoutManager(requireContext())
        recycler.adapter = adapter
        recycler.addItemDecoration(SimpleDecorator(requireContext()))
        dailyWeatherViewModel.getDailyWeather(getLanguage())
    }

    /*private fun applyData() {
        lifecycleScope.launchWhenStarted {
            dailyWeatherViewModel.dailyWeatherFlow.onEach {
                dailyWeatherViewModel.getDailyWeather(getLanguage()).adapter.setItems(dailyWeather.daily)
            }
        }
    }*/

    private fun getLanguage(): String {
        val currentLocale = ConfigurationCompat.getLocales(resources.configuration)[0]
        return currentLocale!!.language
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
