package com.albuquerque.moviecatalog.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.albuquerque.data.ui.MovieUI
import com.albuquerque.domain.usecase.GetFavoritesUseCase
import com.albuquerque.core.mediator.SingleMediatorLiveData
import com.albuquerque.core.viewmodel.BaseViewModel
import kotlinx.coroutines.launch

class FavoritesViewModel(
        private val getFavoritesUseCase: GetFavoritesUseCase
): BaseViewModel() {

    private val _favorites = SingleMediatorLiveData<List<MovieUI>>()
    val favorites = _favorites as LiveData<List<MovieUI>>

    init {
        viewModelScope.launch {
            _favorites.emit(getFavoritesUseCase.invoke().asLiveData())
        }
    }

}