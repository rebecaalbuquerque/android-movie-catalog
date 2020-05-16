package com.albuquerque.moviecatalog.core.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

abstract class BaseViewModel: ViewModel() {

    val onError = MutableLiveData<String>()
    val onStartLoading = MutableLiveData<Any>()
    val onFinishLoading = MutableLiveData<Any>()

    open fun handlerError(exception: Exception) {
        // TODO
        onStartLoading.value = Any()
        onFinishLoading.value = Any()
        onError.value = ""
    }

}