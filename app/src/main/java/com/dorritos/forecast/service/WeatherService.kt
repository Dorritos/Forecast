package com.dorritos.forecast.service

import android.app.Service
import android.content.Intent
import android.os.IBinder

class WeatherService : Service() {

    companion object{
        const val commandKey = "ck"
        const val firstCommand = "1st"
        const val exitCommand = "exit"
        // перечисляешь комманды
        // и ключ
    }

    override fun onBind(intent: Intent?): IBinder? = null

    override fun onCreate() {
        super.onCreate()
    }


    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        intent?.let {
            when(it.getStringExtra(commandKey)){
                firstCommand -> {}
                exitCommand -> {stopSelf()}
            }
        }
        startFG()
        return START_STICKY
    }

    private fun startFG(){
        //ДЗ
        startForeground()
    }


    override fun onDestroy() {
        super.onDestroy()
    }
}