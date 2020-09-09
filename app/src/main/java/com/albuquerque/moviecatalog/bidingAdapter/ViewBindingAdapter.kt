package com.albuquerque.moviecatalog.bidingAdapter

import android.view.View
import androidx.databinding.BindingAdapter
import com.albuquerque.domain.extensions.setGone
import com.albuquerque.domain.extensions.setVisible

@BindingAdapter("isVisible", requireAll = false)
fun isVisible(view: View, visible: Boolean?) {

    visible?.let {
        if(visible)
            view.setVisible()
        else
            view.setGone()
    }

}