package com.coldfier.kmm.utils.coroutines

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class WrappedStateFlow<T: Any>(private val origin: StateFlow<T>) : StateFlow<T> by origin {

    fun subscribe(block: (T) -> Unit): Closeable = subscribeToFlow(block)
}

class WrappedFlow<T: Any>(private val origin: Flow<T>) : Flow<T> by origin {

    fun subscribe(block: (T) -> Unit): Closeable = subscribeToFlow(block)
}

private fun <T> Flow<T>.subscribeToFlow(block: (T) -> Unit): Closeable {
    val context = CoroutineScope(Dispatchers.Main + SupervisorJob())

    this.onEach(block).launchIn(context)

    return object : Closeable {
        override fun close(): Unit = context.cancel()
    }
}

fun <T: Any> StateFlow<T>.toWrappedStateFlow(): WrappedStateFlow<T> = WrappedStateFlow(this)

fun <T : Any> Flow<T>.toWrappedFlow(): WrappedFlow<T> = WrappedFlow(this)