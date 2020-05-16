package com.albuquerque.moviecatalog.core.extensions

import android.view.View

fun View.setVisible() {
    this.visibility = View.VISIBLE
}

fun View.setInisible() {
    this.visibility = View.INVISIBLE
}

fun View.setGone() {
    this.visibility = View.GONE
}