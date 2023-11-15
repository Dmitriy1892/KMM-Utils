package io.github.dmitriy1892.kmm.utils.coroutines.mutex

import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock

inline fun <T> Mutex.withThreadLock(crossinline action: suspend () -> T): T {
    return runBlocking { this@withThreadLock.withLock { action() } }
}