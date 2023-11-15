package io.github.dmitriy1892.kmm.utils.platform

object Config {
    val isDebugBuild: Boolean = isDebug()
}

internal expect fun isDebug(): Boolean