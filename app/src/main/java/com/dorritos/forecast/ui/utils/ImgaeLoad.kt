package com.dorritos.forecast.ui.utils

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide

object ImageLoad {
    fun setImage(url: String, imageView: ImageView) {
        Glide.with(imageView.context).load(url).into(imageView)
    }
}