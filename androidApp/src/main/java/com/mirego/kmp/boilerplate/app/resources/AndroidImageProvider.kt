package com.mirego.kmp.boilerplate.app.resources

import android.content.Context
import com.mirego.kmp.boilerplate.R
import com.mirego.kmp.boilerplate.viewmodel.common.SharedImageResource
import com.mirego.trikot.viewmodels.declarative.configuration.VMDImageProvider
import com.mirego.trikot.viewmodels.declarative.properties.VMDImageResource

class AndroidImageProvider : VMDImageProvider {
    override fun resourceIdForResource(resource: VMDImageResource, context: Context) = when (resource) {
        is SharedImageResource -> when (resource) {
            SharedImageResource.emptyPageIcon -> R.drawable.baseline_question_mark_24
            SharedImageResource.errorPageIcon -> R.drawable.baseline_warning_24
            SharedImageResource.imagePlaceholder -> R.drawable.baseline_image_24
            SharedImageResource.closeIcon -> R.drawable.baseline_close_24
        }

        else -> null
    }
}
