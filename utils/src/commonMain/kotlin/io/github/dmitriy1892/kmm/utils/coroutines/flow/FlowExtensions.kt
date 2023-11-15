package io.github.dmitriy1892.kmm.utils.coroutines.flow

import io.github.dmitriy1892.kmm.utils.coroutines.Closeable
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

fun <T: Any> StateFlow<T>.asWrappedStateFlow(): WrappedStateFlow<T> = WrappedStateFlow(this)

fun <T: Any> MutableStateFlow<T>.asWrappedStateFlow(): WrappedStateFlow<T> =
    this.asStateFlow().asWrappedStateFlow()

fun <T: Any> SharedFlow<T>.asWrappedSharedFlow(): WrappedSharedFlow<T> =
    WrappedSharedFlow(this)

fun <T: Any> MutableSharedFlow<T>.asWrappedSharedFlow(): WrappedSharedFlow<T> =
    this.asSharedFlow().asWrappedSharedFlow()

fun <T : Any> Flow<T>.asWrappedFlow(): WrappedFlow<T> = WrappedFlow(this)

internal fun <T> Flow<T>.subscribeToFlow(block: (T) -> Unit): Closeable {
    val context = CoroutineScope(Dispatchers.Main + SupervisorJob())

    this.onEach(block).launchIn(context)

    return object : Closeable {
        override fun close(): Unit = context.cancel()
    }
}