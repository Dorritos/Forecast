package com.dorritos.forecast.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dorritos.forecast.databinding.FragmentMapBinding
import com.dorritos.forecast.ui.viewmodels.DailyWeatherViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MapFragment : BaseFragment() {

    private var _binding: FragmentMapBinding? = null
    private val binding get() = _binding!!
    private val dailyWeatherViewModel: DailyWeatherViewModel by viewModel()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentMapBinding.inflate(inflater, container, false)
        val root: View = binding.root
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}