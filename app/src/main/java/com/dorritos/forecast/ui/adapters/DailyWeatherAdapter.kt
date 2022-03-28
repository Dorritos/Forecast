package com.dorritos.forecast.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.dorritos.forecast.R
import com.dorritos.forecast.remote.models.current.Daily
import com.dorritos.forecast.ui.utils.ImageLoad

class DailyWeatherAdapter(private val dataSet: List<Daily>) :
    RecyclerView.Adapter<DailyWeatherAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val textViewItemDay: TextView = view.findViewById(R.id.textViewItemDay)
        val textViewItemDate: TextView  = view.findViewById(R.id.textViewItemDate)
        val textViewItemDescription: TextView = view.findViewById(R.id.textViewItemDescription)
        val textViewItemTempMax: TextView = view.findViewById(R.id.textViewItemTempMax)
        val textViewItemTempMin: TextView = view.findViewById(R.id.textViewItemTempMin)
        val imageViewItemWeatherIcon: ImageView = view.findViewById(R.id.imageViewDailyIcon)

    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.daily_weather_item, viewGroup, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.apply {
            textViewItemDay.text = dataSet[position].getDayTime(dataSet[position].dt.toLong())
            textViewItemDescription.text = dataSet[position].weather.first().description
            textViewItemTempMax.text = dataSet[position].temp.getDailyTempCelcium(dataSet[position].temp.max)
            textViewItemTempMin.text = dataSet[position].temp.getDailyTempCelcium(dataSet[position].temp.min)

            val imageUrl = "https://openweathermap.org/img/w/" + dataSet[position].weather.first().icon + ".png";
            ImageLoad.setImage(imageUrl, imageViewItemWeatherIcon)
        }
    }

    override fun getItemCount() = dataSet.size
}

