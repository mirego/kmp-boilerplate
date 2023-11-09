package com.mirego.kmp.boilerplate

import com.mirego.kmp.boilerplate.common.BuildConfig

actual class Platform actual constructor() {
    actual val system = System(
        name = "Android",
        version = android.os.Build.VERSION.SDK_INT.toString()
    )

    actual val locale = java.util.Locale.getDefault().let {
        Locale(
            languageCode = it.language,
            regionCode = it.country
        )
    }

    actual val version = Version(
        name = BuildConfig.VERSION_NAME,
        code = BuildConfig.VERSION_CODE
    )
}
