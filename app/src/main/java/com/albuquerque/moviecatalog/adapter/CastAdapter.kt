package com.albuquerque.moviecatalog.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.albuquerque.moviecatalog.R
import com.albuquerque.data.ui.CastUI
import com.albuquerque.moviecatalog.view.holder.CastViewHolder
import com.albuquerque.core.adapter.BaseAdapter
import com.albuquerque.moviecatalog.databinding.ItemCastBinding
import com.albuquerque.moviecatalog.databinding.ItemCastBindingImpl

class CastAdapter: BaseAdapter<CastUI, CastViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CastViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_cast, parent, false)
        return CastViewHolder(ItemCastBindingImpl(null, view) as ItemCastBinding)
    }

    override fun onBindViewHolder(holder: CastViewHolder, position: Int) {
        holder.bind(getItemAt(position))
    }

}