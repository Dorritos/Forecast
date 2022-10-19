package com.dorritos.forecast

import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import org.koin.android.ext.android.inject

class Settings : Fragment() {

    private val prefs by inject<SharedPreferences>()

    companion object {
        const val systemKey = "systemKey"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getSystemPrefs()
    }

    private fun getSystemPrefs() {
        // view
        val system = prefs.getInt(systemKey, 0)
    }

    private fun saveSystem(system: Int) {
        prefs.edit().putInt(systemKey, system).apply()
    }
}