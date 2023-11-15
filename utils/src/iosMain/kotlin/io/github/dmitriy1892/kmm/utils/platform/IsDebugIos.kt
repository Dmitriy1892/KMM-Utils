package io.github.dmitriy1892.kmm.utils.platform

import kotlin.experimental.ExperimentalNativeApi

@OptIn(ExperimentalNativeApi::class)
internal actual fun isDebug(): Boolean = Platform.isDebugBinary