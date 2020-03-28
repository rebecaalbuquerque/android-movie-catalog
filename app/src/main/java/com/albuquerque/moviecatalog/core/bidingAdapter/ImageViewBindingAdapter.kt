package com.albuquerque.moviecatalog.core.bidingAdapter

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

@BindingAdapter("src")
fun loadImage(imageView: ImageView, src: String) {

    Glide
            .with(imageView.context)
            .load(src)
            .into(imageView)

}