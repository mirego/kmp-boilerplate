package com.mirego.kmp.boilerplate.app.bootstrap

import android.content.Context
import com.mirego.kmp.boilerplate.BuildConfig
import com.mirego.kmp.boilerplate.app.resources.AndroidImageProvider
import com.mirego.kmp.boilerplate.bootstrap.AppEnvironment
import com.mirego.kmp.boilerplate.bootstrap.Bootstrap
import com.mirego.trikot.kword.android.AndroidKWord
import com.mirego.trikot.viewmodels.declarative.configuration.DefaultTextStyleProvider
import com.mirego.trikot.viewmodels.declarative.configuration.TrikotViewModelDeclarative
import java.util.*

class AndroidBootstrap(context: Context) : Bootstrap {

    override val appInformation = AppInformationImpl(context)

    override val environment = when (BuildConfig.FLAVOR.lowercase()) {
        "ci" -> AppEnvironment.DEV
        "store" -> AppEnvironment.PRODUCTION
        else -> AppEnvironment.PRODUCTION
    }

    init {
        if (Locale.getDefault().language.lowercase() == "fr") {
            AndroidKWord.setCurrentLanguageCode("fr")
        } else {
            AndroidKWord.setCurrentLanguageCode("en")
        }

        TrikotViewModelDeclarative.initialize(
            imageProvider = AndroidImageProvider(),
            textStyleProvider = DefaultTextStyleProvider()
        )
    }
}
