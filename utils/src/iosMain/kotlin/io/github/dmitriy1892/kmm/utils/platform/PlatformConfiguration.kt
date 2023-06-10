package io.github.dmitriy1892.kmm.utils.platform

actual class PlatformConfiguration {

    actual companion object {
        actual val isDebugBuild: Boolean = Platform.isDebugBinary
    }
}