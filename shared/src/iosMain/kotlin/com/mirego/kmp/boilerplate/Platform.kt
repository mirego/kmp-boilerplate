package com.mirego.kmp.boilerplate

import platform.Foundation.NSBundle
import platform.Foundation.NSLocale
import platform.Foundation.currentLocale
import platform.Foundation.languageCode
import platform.Foundation.regionCode
import platform.UIKit.UIDevice

actual class Platform actual constructor() {
    actual val system = UIDevice.currentDevice.let {
        System(
            name = it.systemName,
            version = it.systemVersion
        )
    }

    actual val locale = NSLocale.currentLocale.let {
        Locale(
            languageCode = it.languageCode,
            regionCode = it.regionCode
        )
    }

    actual val version = NSBundle.mainBundle().infoDictionary()!!.let {
        Version(
            name = it["CFBundleShortVersionString"] as String,
            code = (it["CFBundleVersion"] as String).toInt()
        )
    }
}
