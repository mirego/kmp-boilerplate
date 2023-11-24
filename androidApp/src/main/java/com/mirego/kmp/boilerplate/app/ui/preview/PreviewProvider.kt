package com.mirego.kmp.boilerplate.app.ui.preview

import androidx.compose.runtime.Composable
import com.mirego.kmp.boilerplate.BuildConfig
import com.mirego.kmp.boilerplate.app.resources.AndroidImageProvider
import com.mirego.kmp.boilerplate.viewmodel.factory.ViewModelFactoryPreview
import com.mirego.trikot.kword.android.PreviewI18N
import com.mirego.trikot.viewmodels.declarative.configuration.TrikotViewModelDeclarative

@Composable
fun PreviewProvider(content: @Composable (ViewModelFactoryPreview) -> Unit) {
    TrikotViewModelDeclarative.initialize(AndroidImageProvider())
    val viewModelFactoryPreview = ViewModelFactoryPreview(
        i18N = PreviewI18N(BuildConfig.KWORD_TRANSLATION_FILE_PATH)
    )

    content(viewModelFactoryPreview)
}
