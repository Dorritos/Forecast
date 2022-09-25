package com.dorritos.forecast.ui.fragments

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.*
import android.widget.SearchView
import androidx.core.content.ContextCompat
import androidx.core.os.ConfigurationCompat
import androidx.lifecycle.lifecycleScope
import com.dorritos.forecast.R
import com.dorritos.forecast.databinding.FragmentTodayBinding
import com.dorritos.forecast.remote.models.current.CurrentWeather
import com.dorritos.forecast.service.WeatherService
import com.dorritos.forecast.ui.utils.ThemePicker
import com.dorritos.forecast.ui.viewmodels.CurrentWeatherViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.stateViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class CurrentWeatherFragment : BaseFragment() {

    private var _binding: FragmentTodayBinding? = null
    private var search: SearchView? = null
    private val binding get() = _binding!!
    private val currentWeatherViewModel: CurrentWeatherViewModel by viewModel()
    private val prefs: SharedPreferences by inject()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        setHasOptionsMenu(true)
        _binding = FragmentTodayBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onPrepareOptionsMenu(menu: Menu) {
        super.onPrepareOptionsMenu(menu)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        search = menu.getItem(R.id.search) as SearchView
        search?.let {
            it.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(p0: String?): Boolean {
                    currentWeatherViewModel.getWeatherByCity(p0)
                    prefs.edit().putString(p0, p0).apply()
                    return true
                }

                override fun onQueryTextChange(p0: String?): Boolean {
                    return false
                }
            })
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.exit_button -> {
                //Добавить категорию к с интенту для старта сервиса
                val intent = Intent().also {
                    it.putExtra(WeatherService.commandKey, WeatherService.exitCommand)
                    it.addCategory(Intent.ACTION_MAIN)
                }
                ContextCompat.startForegroundService(requireContext(), intent)
                requireActivity().finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        subscribe()
        //currentWeatherViewModel.getWeatherByCity(prefs.getString(key, null))
    }

    private fun subscribe() {
        lifecycleScope.launchWhenStarted {
            currentWeatherViewModel.currentWeatherFlow.onEach {
                it?.let {
                    initTempViews(it)
                    ThemePicker.applyWeatherCharacter(it, requireContext(), binding)
                    applyWeatherDescription(it)
                }
            }.collect()
        }
    }

    private fun getLanguage(): String {
        val currentLocale = ConfigurationCompat.getLocales(resources.configuration)[0]
        return currentLocale!!.language
    }

    private fun initTempViews(currentWeather: CurrentWeather) {
        binding.apply {
            textViewCountry.text = currentWeather.sys.getCountryName()
            textViewCity.text = currentWeather.name
            textViewTempMinValue.text = currentWeather.main.getCelcium(currentWeather.main.temp_min)
            textViewTempMaxValue.text = currentWeather.main.getCelcium(currentWeather.main.temp_max)
            textViewTemp.text = currentWeather.main.getCelcium(currentWeather.main.temp)
            textViewFeelsLikeValue.text = currentWeather.main.getCelcium(currentWeather.main.feels_like)
        }
    }



    private fun applyWeatherDescription(currentWeather: CurrentWeather) {
        val textViewDescription = binding.textViewDescription
        textViewDescription.text = currentWeather.weather.first().description
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}