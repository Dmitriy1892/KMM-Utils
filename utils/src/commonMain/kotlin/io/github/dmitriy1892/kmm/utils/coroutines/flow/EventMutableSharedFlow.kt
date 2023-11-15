package io.github.dmitriy1892.kmm.utils.coroutines.flow

import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.first

class EventMutableSharedFlow<T>(
    private val sharedFlow: MutableSharedFlow<T> = MutableSharedFlow()
) : MutableSharedFlow<T> by sharedFlow {

    override suspend fun emit(value: T) {
        sharedFlow.subscriptionCount.first { it > 0 }
        sharedFlow.emit(value)
    }

    override suspend fun collect(collector: FlowCollector<T>): Nothing {
        sharedFlow.collect(collector)
    }
}