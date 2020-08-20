package com.albuquerque.moviecatalog.core.bidingAdapter

import android.widget.ImageButton
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.albuquerque.moviecatalog.R
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy

@BindingAdapter("src", requireAll = false)
fun loadImage(imageView: ImageView, src: String?) {

    Glide
            .with(imageView.context)
            .load(src)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .placeholder(R.drawable.ic_launcher_foreground)
            .into(imageView)

}

@BindingAdapter("app:isFavorite", requireAll = false)
fun setFavoriteIcon(imageButton: ImageButton, favorite: Boolean = false) {

    if(favorite)
        imageButton.setImageResource(R.drawable.ic_star_on)
    else
        imageButton.setImageResource(R.drawable.ic_star_off)

}