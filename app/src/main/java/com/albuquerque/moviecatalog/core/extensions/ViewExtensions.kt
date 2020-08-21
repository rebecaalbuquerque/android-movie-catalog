package com.albuquerque.moviecatalog.core.extensions

import android.content.res.Resources
import android.view.View
import androidx.core.content.ContextCompat
import com.albuquerque.moviecatalog.R
import com.google.android.material.snackbar.Snackbar

fun View.setVisible() {
    this.visibility = View.VISIBLE
}

fun View.setInisible() {
    this.visibility = View.INVISIBLE
}

fun View.setGone() {
    this.visibility = View.GONE
}

fun View.showSnackbar(message: String) {
    Snackbar.make(this, message, Snackbar.LENGTH_LONG).setBackgroundTint(ContextCompat.getColor(this.context, R.color.colorAccent)).show()
}

val Int.dp: Int
    get() = (this * Resources.getSystem().displayMetrics.density + 0.5f).toInt()

val Float.dp: Int
    get() = (this * Resources.getSystem().displayMetrics.density + 0.5f).toInt()