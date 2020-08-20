package com.albuquerque.moviecatalog.app.view.holder

import androidx.databinding.ViewDataBinding
import com.albuquerque.moviecatalog.app.data.ui.CastUI
import com.albuquerque.moviecatalog.core.adapter.BaseAdapter
import com.albuquerque.moviecatalog.databinding.ItemCastBinding

class CastViewHolder(binding: ViewDataBinding): BaseAdapter.BaseViewHolder<CastUI>(binding) {

    override fun bind(item: CastUI) {
        (binding as ItemCastBinding).cast = item
    }

}