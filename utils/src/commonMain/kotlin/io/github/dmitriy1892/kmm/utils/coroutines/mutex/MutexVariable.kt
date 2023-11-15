package io.github.dmitriy1892.kmm.utils.coroutines.mutex

import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock

/**
 * Class-wrapper for thread-safe access to data [T]
 *
 * @constructor
 *
 * @param initialValue initial value
 */
class MutexVariable<T>(initialValue: T) {

    /**
     * [value] - field for storing a [T] variable
     */
    private var value: T = initialValue

    /**
     * [mutex] - [Mutex] object for thread-safe access to [value]
     */
    private val mutex = Mutex()

    /**
     * Thread-safe set new [value] into [MutexVariable.value] field
     *
     * @param value new [T] value
     */
    suspend fun set(value: T) {
        mutex.withLock { this.value = value }
    }

    /**
     * Thread-blocking synchronous set new [value] into [MutexVariable.value] field
     *
     * @param value new [T] value
     */
    fun setBlocking(value: T) {
        mutex.withThreadLock {
            this.value = value
        }
    }

    /**
     * Thread-safe get [MutexVariable.value]
     *
     * @return [value] - [MutexVariable.value]
     */
    suspend fun get(): T = mutex.withLock { value }

    /**
     * Thread-blocking synchronous get [MutexVariable.value]
     *
     * @return [value] - [MutexVariable.value]
     */
    fun getBlocking(): T = mutex.withThreadLock { value }

    /**
     * Thread-safe update [MutexVariable.value]
     *
     * @param transformation lambda function for transforming [MutexVariable.value]
     * to a new one with [T] type
     */
    suspend fun update(transformation: suspend (T) -> T) {
        return mutex.withLock {
            value = transformation(value)
        }
    }

    /**
     * Thread-blocking synchronous update [MutexVariable.value]
     *
     * @param transformation lambda function for transforming [MutexVariable.value]
     * to a new one with [T] type
     */
    fun updateBlocking(transformation: suspend (T) -> T) {
        return mutex.withThreadLock {
            value = transformation(value)
        }
    }
}