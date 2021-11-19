package com.mirego.kmp.boilerplate

import kotlinx.browser.window

actual class Platform actual constructor() {
    actual val platform: String = "${window.navigator.userAgent} ${window.navigator.platform}"
}
