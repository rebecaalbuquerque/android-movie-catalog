package com.albuquerque.moviecatalog.app.viewmodel

import androidx.lifecycle.ViewModel
import com.albuquerque.moviecatalog.app.usecase.GetPopularUseCase

class MoviesViewModel(
        val getPopularUseCase: GetPopularUseCase
): ViewModel() {



}