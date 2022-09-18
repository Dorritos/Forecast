package com.dorritos.forecast

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.location.LocationManager
import androidx.core.app.ActivityCompat

class LocationHelper(private val context: Context) {

    private val service = context.getSystemService(Context.LOCATION_SERVICE) as LocationManager

    fun getLocation(location: String): LatLon {
        if (ActivityCompat.checkSelfPermission(
                context,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                context, Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            val a = service.getLastKnownLocation(LocationManager.GPS_PROVIDER)
            if (a?.latitude != null && a.longitude != null){
                LatLon(a.latitude, a.longitude)
            }
        }
    }
}