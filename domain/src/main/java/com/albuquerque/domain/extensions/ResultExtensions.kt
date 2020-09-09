package com.albuquerque.domain.extensions

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

fun <T> Result<T>.asFlow(): Flow<Result<T>> = flowOf(this)