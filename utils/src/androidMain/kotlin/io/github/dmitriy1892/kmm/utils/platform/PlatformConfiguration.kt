package io.github.dmitriy1892.kmm.utils.platform

import android.content.Context
import io.github.dmitriy1892.kmm.utils.BuildConfig

actual class PlatformConfiguration(val androidContext: Context) {

    actual companion object {
        actual val isDebugBuild: Boolean = BuildConfig.DEBUG
    }
}