package com.dorritos.forecast.service

import android.app.Service
import android.content.Intent
import android.os.IBinder
import androidx.core.app.NotificationCompat
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.content.ContextCompat

class WeatherService : Service() {

    companion object{
        const val key = "key"
        const val startCommand = "1st"
        const val exitCommand = "exit"
        const val channelId = "CHANNEL_ID"
    }

    override fun onBind(intent: Intent?): IBinder? = null

    override fun onCreate() {
        super.onCreate()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        intent?.let {
            when(it.getStringExtra(key)){
                startCommand -> {

                }
                exitCommand -> {
                    stopSelf()
                }
            }
        }
        startFG()
        return START_STICKY
    }

    private fun startFG() {
        
    }

    override fun onDestroy() {
        super.onDestroy()
    }
}