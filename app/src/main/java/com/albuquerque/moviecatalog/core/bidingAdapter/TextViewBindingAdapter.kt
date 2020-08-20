package com.albuquerque.moviecatalog.core.bidingAdapter

import android.annotation.SuppressLint
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.albuquerque.moviecatalog.app.utils.StatusSearch
import com.albuquerque.moviecatalog.core.extensions.setGone
import com.albuquerque.moviecatalog.core.extensions.setVisible

@SuppressLint("SetTextI18n")
@BindingAdapter("app:search_status", "app:query", requireAll = false)
fun setSearchStatus(textView: TextView, statusSearch: StatusSearch?, query: String?) {

    if(statusSearch != null) {
        when(statusSearch) {
            StatusSearch.BEFORE_SEARCH -> {
                textView.setVisible()
                textView.text = "Você ainda não iniciou uma busca."
            }

            StatusSearch.EMPTY -> {
                if( query != null) {
                    textView.setVisible()
                    textView.text = "Nenhum resultado encontrado para \"$query\"."
                }
            }

            StatusSearch.ERROR -> {
                textView.setVisible()
                textView.text = "Ocorreu um erro inesperado, tente novamente."
            }

            StatusSearch.SUCCESS -> {
                textView.setGone()
            }

        }
    }

}