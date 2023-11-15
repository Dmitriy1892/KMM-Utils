package io.github.dmitriy1892.kmm.utils.coroutines.flow

import io.github.dmitriy1892.kmm.utils.coroutines.Closeable
import kotlinx.coroutines.flow.SharedFlow

class WrappedSharedFlow<T: Any>(private val origin: SharedFlow<T>) : SharedFlow<T> by origin {

    fun subscribe(block: (T) -> Unit): Closeable = subscribeToFlow(block)
}