package com.mirego.kmp.boilerplate.app.bootstrap

import android.content.Context
import com.mirego.kmp.boilerplate.BuildConfig
import com.mirego.kmp.boilerplate.bootstrap.AppInformation
import com.mirego.kmp.boilerplate.model.Language
import com.mirego.kmp.boilerplate.model.Locale
import kotlinx.coroutines.flow.MutableStateFlow

class AppInformationImpl(context: Context) : AppInformation {
    override val locale: MutableStateFlow<Locale> = AppInformation.buildLocaleMutableStateFlow(currentLocale())

    fun updateLocale() {
        locale.value = currentLocale()
    }

    private fun currentLocale() = Locale(
        if (java.util.Locale.getDefault().language.lowercase() == "fr") Language.FRENCH else Language.ENGLISH,
        java.util.Locale.getDefault().country
    )

    override val versionNumber: String = "${BuildConfig.VERSION_NAME}.${BuildConfig.VERSION_CODE}"
    override val diskCachePath: String = context.cacheDir.toString()
}
