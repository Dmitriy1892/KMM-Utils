package io.github.dmitriy1892.kmm.utils.coroutines.flow

import io.github.dmitriy1892.kmm.utils.coroutines.Closeable
import kotlinx.coroutines.flow.Flow

class WrappedFlow<T: Any>(private val origin: Flow<T>) : Flow<T> by origin {

    fun subscribe(block: (T) -> Unit): Closeable = subscribeToFlow(block)
}