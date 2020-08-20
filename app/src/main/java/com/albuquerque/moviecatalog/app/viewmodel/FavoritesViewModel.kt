package com.albuquerque.moviecatalog.app.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.albuquerque.moviecatalog.app.data.ui.MovieUI
import com.albuquerque.moviecatalog.app.usecase.GetFavoritesUseCase
import com.albuquerque.moviecatalog.core.mediator.SingleMediatorLiveData
import com.albuquerque.moviecatalog.core.viewmodel.BaseViewModel
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