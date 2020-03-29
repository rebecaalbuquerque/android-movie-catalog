package com.albuquerque.moviecatalog.core.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.lang.Exception

abstract class BaseViewModel: ViewModel() {

    val onError = MutableLiveData<String>()
    val onLoading = MutableLiveData<Boolean>()

    open fun handlerError(exception: Exception) {
        // TODO
        onLoading.value = false
        onError.value = ""
    }

}