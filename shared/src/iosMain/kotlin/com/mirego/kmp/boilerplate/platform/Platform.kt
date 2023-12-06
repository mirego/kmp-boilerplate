package com.mirego.kmp.boilerplate.platform

import platform.Foundation.NSBundle
import platform.Foundation.NSLocale
import platform.Foundation.currentLocale
import platform.Foundation.languageCode
import platform.Foundation.regionCode
import platform.UIKit.UIDevice

actual fun Platform(): Platform = IOSPlatform()

private class IOSPlatform : Platform {
    override val system = UIDevice.currentDevice.let {
        System(
            name = it.systemName,
            version = it.systemVersion
        )
    }

    override val locale = NSLocale.currentLocale.let {
        Locale(
            languageCode = it.languageCode,
            regionCode = it.regionCode
        )
    }

    override val version = NSBundle.mainBundle().infoDictionary()!!.let {
        Version(
            name = it["CFBundleShortVersionString"] as String,
            code = (it["CFBundleVersion"] as String).toInt()
        )
    }
}
