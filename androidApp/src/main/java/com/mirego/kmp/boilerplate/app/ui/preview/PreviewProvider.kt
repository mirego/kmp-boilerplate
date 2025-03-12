package com.mirego.kmp.boilerplate.app.ui.preview

import androidx.compose.runtime.Composable
import com.mirego.kmp.boilerplate.BuildConfig
import com.mirego.kmp.boilerplate.app.resources.AndroidImageProvider
import com.mirego.kmp.boilerplate.viewmodel.factory.ViewModelPreviewsFactory
import com.mirego.pilot.components.ui.PilotResources
import com.mirego.trikot.kword.android.PreviewI18N

@Composable
fun PreviewProvider(content: @Composable (ViewModelPreviewsFactory) -> Unit) {
    val viewModelPreviewsFactory = ViewModelPreviewsFactory(
        i18N = PreviewI18N(BuildConfig.KWORD_TRANSLATION_FILE_PATH)
    )

    PilotResources(
        imageResourceProvider = AndroidImageProvider(),
        content = {
            content(viewModelPreviewsFactory)
        }
    )
}
