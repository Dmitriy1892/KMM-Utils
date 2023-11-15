package io.github.dmitriy1892.kmm.utils.coroutines.flow

import io.github.dmitriy1892.kmm.utils.coroutines.Closeable
import kotlinx.coroutines.flow.StateFlow

class WrappedStateFlow<T: Any>(private val origin: StateFlow<T>) : StateFlow<T> by origin {

    fun subscribe(block: (T) -> Unit): Closeable = subscribeToFlow(block)
}