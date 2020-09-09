package com.albuquerque.core.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

abstract class BaseViewModel: ViewModel() {

    val onSnackBarError = MutableLiveData<String>()
    val onLayoutError = MutableLiveData<Any>()
    val onLoading = MutableLiveData<Boolean>()

}