package com.mirego.kmp.boilerplate.viewmodel.common

import com.mirego.pilot.components.PilotButton
import com.mirego.pilot.components.PilotImageResource
import com.mirego.pilot.components.content.PilotLocalImageContent
import kotlinx.coroutines.CoroutineScope

class EmptyViewModelImpl(
    icon: PilotImageResource = SharedImageResource.emptyPageIcon,
    title: String,
    message: String,
    override val actionButton: PilotButton<String>?,
    override val secondaryActionButton: PilotButton<String>? = null,
    coroutineScope: CoroutineScope
) : EmptyViewModel() {

    override val icon = PilotLocalImageContent(icon)

    override val title = title

    override val message = message
}
