package com.dorritos.forecast.ui.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.*
import android.widget.SearchView
import androidx.core.content.ContextCompat
import androidx.core.os.ConfigurationCompat
import androidx.lifecycle.lifecycleScope
import com.dorritos.forecast.R
import com.dorritos.forecast.databinding.FragmentTodayBinding
import com.dorritos.forecast.remote.models.current.CurrentWeather
import com.dorritos.forecast.ui.viewmodels.CurrentWeatherViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import org.koin.androidx.viewmodel.ext.android.viewModel

class CurrentWeatherFragment : BaseFragment() {

    private var _binding: FragmentTodayBinding? = null
    private var search: SearchView? = null
    private val binding get() = _binding!!
    private val currentWeatherViewModel: CurrentWeatherViewModel by viewModel()

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
                    // эту строку р0 проводишь во вью модель с целью оределить лат лонг по введённому городу
                    currentWeatherViewModel.currentWeatherFlow.value.toString()
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
                requireActivity().finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        subscribe()
        currentWeatherViewModel.getCurrentWeather(getLanguage())
    }

    private fun subscribe() {
        lifecycleScope.launchWhenStarted {
            currentWeatherViewModel.currentWeatherFlow.onEach {
                it?.let {
                    initTempViews(it)
                    applyWeatherCharacter(it)
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

    @SuppressLint("ResourceAsColor")
    private fun applyWeatherCharacter(currentWeather: CurrentWeather) {
        val temp = currentWeather.main.temp.toInt()
        val characterImage = binding.imageViewWeatherIcon
        val detailsBackground = binding.constraintLayout2
        when (temp) {
            in 35..40 -> {
                detailsBackground.setBackgroundColor(
                    ContextCompat.getColor(
                        requireContext(),
                        R.color.temp_40
                    )
                )
                characterImage.setImageResource(R.drawable.ic_temp_40)
            }
            in 30..35 -> {
                detailsBackground.setBackgroundColor(
                    ContextCompat.getColor(
                        requireContext(),
                        R.color.temp_35
                    )
                )
                characterImage.setImageResource(R.drawable.ic_temp_35)
            }
            in 25..30 -> {
                detailsBackground.setBackgroundColor(
                    ContextCompat.getColor(
                        requireContext(),
                        R.color.temp_30
                    )
                )
                characterImage.setImageResource(R.drawable.ic_temp_30)
            }
            in 20..25 -> {
                detailsBackground.setBackgroundColor(
                    ContextCompat.getColor(
                        requireContext(),
                        R.color.temp_25
                    )
                )
                characterImage.setImageResource(R.drawable.ic_temp_25)
            }
            in 15..20 -> {
                detailsBackground.setBackgroundColor(
                    ContextCompat.getColor(
                        requireContext(),
                        R.color.temp_20
                    )
                )
                characterImage.setImageResource(R.drawable.ic_temp_20)
            }
            in 10..15 -> {
                detailsBackground.setBackgroundColor(
                    ContextCompat.getColor(
                        requireContext(),
                        R.color.temp_15
                    )
                )
                characterImage.setImageResource(R.drawable.ic_temp_15)
            }
            in 5..10 -> {
                detailsBackground.setBackgroundColor(
                    ContextCompat.getColor(
                        requireContext(),
                        R.color.temp_10
                    )
                )
                characterImage.setImageResource(R.drawable.ic_temp_10)
            }
            in 0..5 -> {
                detailsBackground.setBackgroundColor(
                    ContextCompat.getColor(
                        requireContext(),
                        R.color.temp_5
                    )
                )
                characterImage.setImageResource(R.drawable.ic_temp_5)
            }
            in -5..0 -> {
                detailsBackground.setBackgroundColor(
                    ContextCompat.getColor(
                        requireContext(),
                        R.color.temp_0
                    )
                )
                characterImage.setImageResource(R.drawable.ic_temp_0)
            }
            in -1 downTo -5 -> {
                detailsBackground.setBackgroundColor(
                    ContextCompat.getColor(
                        requireContext(),
                        R.color.temp_minus_5
                    )
                )
                characterImage.setImageResource(R.drawable.ic_temp_minus_5)
            }
            in -5 downTo -10 -> {
                detailsBackground.setBackgroundColor(
                    ContextCompat.getColor(
                        requireContext(),
                        R.color.temp_minus_10
                    )
                )
                characterImage.setImageResource(R.drawable.ic_temp_minus_10)
            }
            in -10 downTo -15 -> {
                detailsBackground.setBackgroundColor(
                    ContextCompat.getColor(
                        requireContext(),
                        R.color.temp_minus_15
                    )
                )
                characterImage.setImageResource(R.drawable.ic_temp_minus_15)
            }
            in -15 downTo -20 -> {
                detailsBackground.setBackgroundColor(
                    ContextCompat.getColor(
                        requireContext(),
                        R.color.temp_minus_20
                    )
                )
                characterImage.setImageResource(R.drawable.ic_temp_minus_20)
            }
            in -20 downTo -25 -> {
                detailsBackground.setBackgroundColor(
                    ContextCompat.getColor(
                        requireContext(),
                        R.color.temp_minus_25
                    )
                )
                characterImage.setImageResource(R.drawable.ic_temp_minus_25)
            }
            in -25 downTo -30 -> {
                detailsBackground.setBackgroundColor(
                    ContextCompat.getColor(
                        requireContext(),
                        R.color.temp_minus_30
                    )
                )
                characterImage.setImageResource(R.drawable.ic_temp_minus_30)
            }
            in -30 downTo -35 -> {
                detailsBackground.setBackgroundColor(
                    ContextCompat.getColor(
                        requireContext(),
                        R.color.temp_minus_35
                    )
                )
                characterImage.setImageResource(R.drawable.ic_temp_minus_35)
            }
            else -> characterImage.setImageResource(R.drawable.ic_temp_0)
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