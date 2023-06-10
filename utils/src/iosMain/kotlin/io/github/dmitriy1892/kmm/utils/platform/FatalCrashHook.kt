package io.github.dmitriy1892.kmm.utils.platform

object FatalCrashHook {

    fun doBeforeAppCrashing(callback: (Throwable) -> Unit) {
        setUnhandledExceptionHook {
            callback(it)
            println("---------------------KOTLIN MULTIPLATFORM ERROR STACKTRACE START---------------------")
            it.printStackTrace()
            println("---------------------KOTLIN MULTIPLATFORM ERROR STACKTRACE END---------------------")
        }
    }
}