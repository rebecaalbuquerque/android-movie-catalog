package com.albuquerque.moviecatalog.core.adapter

import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

abstract class BaseAdapter<T, Holder: BaseAdapter.BaseViewHolder<T>>: RecyclerView.Adapter<Holder>() {

    var items: List<T> = listOf()

    open fun refresh(items: List<T>) {
        this.items = items
        notifyDataSetChanged()
    }

    open fun getItemAt(position: Int): T = items[position]

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size

    abstract class BaseViewHolder<T>(var binding: ViewDataBinding): RecyclerView.ViewHolder(binding.root), IBindView<T> {
        abstract override fun bind(item: T)
    }

}

interface IBindView<T> {
    fun bind(item: T)
}