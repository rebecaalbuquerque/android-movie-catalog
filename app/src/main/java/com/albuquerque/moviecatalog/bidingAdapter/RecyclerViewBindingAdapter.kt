package com.albuquerque.moviecatalog.bidingAdapter

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.albuquerque.core.adapter.BaseAdapter

@Suppress("UNCHECKED_CAST")
@BindingAdapter("app:items", requireAll = false)
fun <T> setItems(recyclerView: RecyclerView, items: List<T>?) {
    items?.let {
        (recyclerView.adapter as? BaseAdapter<T, BaseAdapter.BaseViewHolder<T>>)?.refresh(items)
    }
}