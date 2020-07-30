package com.albuquerque.moviecatalog.core.bidingAdapter

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.albuquerque.moviecatalog.R
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy

@BindingAdapter("src")
fun loadImage(imageView: ImageView, src: String) {

    Glide
            .with(imageView.context)
            .load(src)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .placeholder(R.drawable.ic_launcher_foreground)
            .into(imageView)

}