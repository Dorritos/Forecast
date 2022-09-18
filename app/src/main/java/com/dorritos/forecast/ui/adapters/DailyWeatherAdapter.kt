package com.dorritos.forecast.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.dorritos.forecast.R
import com.dorritos.forecast.remote.models.current.Daily
import com.dorritos.forecast.ui.DailyDiffUtil
import com.dorritos.forecast.ui.utils.ImageLoad
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers

class DailyWeatherAdapter(private val scope: LifecycleCoroutineScope) :
    RecyclerView.Adapter<DailyWeatherAdapter.ViewHolder>() {


    private var itemList = listOf<Daily>()

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
        val item = getItem(position)
        viewHolder.apply {
            textViewItemDay.text = item.getDayTime(item.dt.toLong())
            textViewItemDescription.text = item.weather.first().description
            textViewItemTempMax.text = item.temp.getDailyTempCelcium(item.temp.max)
            textViewItemTempMin.text = item.temp.getDailyTempCelcium(item.temp.min)

            scope.launchWhenStarted {
                val imageUrl = "https://openweathermap.org/img/w/" + item.weather.first().icon + ".png"
                ImageLoad.setImage(imageUrl, imageViewItemWeatherIcon)
            }
        }
    }

    private fun getItem(position: Int): Daily = itemList[position]

    override fun getItemCount() = itemList.size

    fun setItems(items: List<Daily>){
        val result = DiffUtil.calculateDiff(DailyDiffUtil(itemList, items))
        itemList = items
        result.dispatchUpdatesTo(this)

    }
}

