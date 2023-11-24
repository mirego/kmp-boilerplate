package com.mirego.kmp.boilerplate.app.bootstrap

import android.content.Context
import com.mirego.kmp.boilerplate.BuildConfig
import com.mirego.kmp.boilerplate.bootstrap.AppInformation
import com.mirego.kmp.boilerplate.model.Language
import com.mirego.kmp.boilerplate.model.Locale

class AppInformationImpl(context: Context) : AppInformation {
    override val locale: Locale = Locale(
        if (java.util.Locale.getDefault().language.lowercase() == "fr") Language.FRENCH else Language.ENGLISH,
        java.util.Locale.getDefault().country
    )

    override val versionNumber: String = "${BuildConfig.VERSION_NAME}.${BuildConfig.VERSION_CODE}"
    override val diskCachePath: String = context.cacheDir.toString()
}
