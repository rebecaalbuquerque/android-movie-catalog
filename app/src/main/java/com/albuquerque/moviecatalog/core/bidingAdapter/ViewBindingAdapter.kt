package com.albuquerque.moviecatalog.core.bidingAdapter

import android.view.View
import androidx.databinding.BindingAdapter
import com.albuquerque.moviecatalog.core.extensions.setGone
import com.albuquerque.moviecatalog.core.extensions.setVisible

@BindingAdapter("isVisible", requireAll = false)
fun isVisible(view: View, visible: Boolean?) {

    visible?.let {
        if(visible)
            view.setVisible()
        else
            view.setGone()
    }

}