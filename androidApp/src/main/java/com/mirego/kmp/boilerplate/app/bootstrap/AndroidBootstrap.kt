package com.mirego.kmp.boilerplate.app.bootstrap

import android.content.Context
import com.mirego.kmp.boilerplate.BuildConfig
import com.mirego.kmp.boilerplate.analytics.SharedAnalyticsConfiguration
import com.mirego.kmp.boilerplate.app.analytics.AndroidSharedAnalyticsService
import com.mirego.kmp.boilerplate.app.resources.AndroidImageProvider
import com.mirego.kmp.boilerplate.bootstrap.AppEnvironment
import com.mirego.kmp.boilerplate.bootstrap.Bootstrap
import com.mirego.kmp.boilerplate.bootstrap.LocaleUtils
import com.mirego.trikot.kword.android.AndroidKWord
import com.mirego.trikot.viewmodels.declarative.configuration.DefaultTextStyleProvider
import com.mirego.trikot.viewmodels.declarative.configuration.TrikotViewModelDeclarative

class AndroidBootstrap(context: Context) : Bootstrap {

    override val appInformation = AppInformationImpl(context)

    override val environment = when (BuildConfig.FLAVOR.lowercase()) {
        "ci" -> AppEnvironment.DEV
        "store" -> AppEnvironment.PRODUCTION
        else -> AppEnvironment.PRODUCTION
    }

    init {
        AndroidKWord.setCurrentLanguageCode(LocaleUtils.supportedLanguageCode())

        TrikotViewModelDeclarative.initialize(
            imageProvider = AndroidImageProvider(),
            textStyleProvider = DefaultTextStyleProvider()
        )

        val analyticsEnabled = !BuildConfig.DEBUG
        SharedAnalyticsConfiguration.analyticsManager = AndroidSharedAnalyticsService(
            context = context,
            analyticsEnabled = analyticsEnabled
        )
    }
}
