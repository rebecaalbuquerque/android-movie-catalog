package com.albuquerque.moviecatalog.core.bidingAdapter

import android.annotation.SuppressLint
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.albuquerque.moviecatalog.R
import com.albuquerque.moviecatalog.app.utils.StatusSearch
import com.albuquerque.moviecatalog.core.extensions.dp
import com.albuquerque.moviecatalog.core.extensions.setGone
import com.albuquerque.moviecatalog.core.extensions.setVisible

@SuppressLint("SetTextI18n")
@BindingAdapter("app:search_status", "app:query", requireAll = false)
fun setSearchStatus(textView: TextView, statusSearch: StatusSearch?, query: String?) {

    if(statusSearch != null) {
        when(statusSearch) {
            StatusSearch.BEFORE_SEARCH -> {
                textView.apply {
                    setVisible()
                    compoundDrawablePadding = 8.dp
                    setCompoundDrawablesRelativeWithIntrinsicBounds(0, R.drawable.ic_feedback_search, 0, 0)
                    text = "Você ainda não iniciou uma busca."
                }
            }

            StatusSearch.EMPTY -> {
                if( query != null) {
                    textView.apply {
                        setVisible()
                        compoundDrawablePadding = 8.dp
                        setCompoundDrawablesRelativeWithIntrinsicBounds(0, R.drawable.ic_dissatisfied, 0, 0)
                        text = "Nenhum resultado encontrado para \"$query\"."
                    }
                }
            }

            StatusSearch.ERROR -> {
                textView.apply {
                    setVisible()
                    compoundDrawablePadding = 8.dp
                    setCompoundDrawablesRelativeWithIntrinsicBounds(0, R.drawable.ic_warning, 0, 0)
                    text = "Ocorreu um erro inesperado, tente novamente."
                }
            }

            StatusSearch.SUCCESS -> {
                textView.setGone()
            }

        }
    }

}