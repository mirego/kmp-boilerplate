package com.mirego.kmp.boilerplate.app.resources

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import com.mirego.kmp.boilerplate.R
import com.mirego.kmp.boilerplate.viewmodel.common.SharedImageResource
import com.mirego.pilot.components.PilotImageResource
import com.mirego.pilot.components.ui.PilotImageResourceProvider

class AndroidImageProvider : PilotImageResourceProvider {
    @Composable
    override fun painterForResource(resource: PilotImageResource): Painter? =
        when (resource) {
            is SharedImageResource -> when (resource) {
                SharedImageResource.emptyPageIcon -> painterResource(R.drawable.baseline_question_mark_24)
                SharedImageResource.errorPageIcon -> painterResource(R.drawable.baseline_warning_24)
                SharedImageResource.imagePlaceholder -> painterResource(R.drawable.baseline_image_24)
                SharedImageResource.closeIcon -> painterResource(R.drawable.baseline_close_24)
            }

            else -> null
        }
}
