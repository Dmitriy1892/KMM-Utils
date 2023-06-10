package io.github.dmitriy1892.kmm.utils.delegates

import io.github.dmitriy1892.kmm.utils.coroutines.withThreadLock
import kotlinx.coroutines.sync.Mutex
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

class MutableLazy<T>(val initializer: () -> T) : ReadWriteProperty<Any, T> {

    private val mutex = Mutex()
    private var value: Any? = null

    override fun getValue(thisRef: Any, property: KProperty<*>): T {
        if (value == null) {
            mutex.withThreadLock {
                if (value == null) value = initializer()
            }
        }

        return value as T
    }

    override fun setValue(thisRef: Any, property: KProperty<*>, value: T) {
        mutex.withThreadLock {
            this.value = value
        }
    }
}

fun <T> mutableLazy(initializer: () -> T): MutableLazy<T> = MutableLazy(initializer)