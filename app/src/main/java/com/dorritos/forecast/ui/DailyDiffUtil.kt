package com.dorritos.forecast.ui

import androidx.recyclerview.widget.DiffUtil
import com.dorritos.forecast.remote.models.current.Daily

class DailyDiffUtil(
    private val oldList: List<Daily>,
    private val newList: List<Daily>,
): DiffUtil.Callback() {

    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] == newList[newItemPosition]
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] == newList[newItemPosition]
    }
}