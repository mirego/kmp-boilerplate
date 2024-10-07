package com.mirego.kmp.boilerplate.viewmodel.common

import com.mirego.pilot.components.PilotImageResource
import com.mirego.pilot.components.content.PilotLocalImageContent
import com.mirego.trikot.viewmodels.declarative.components.VMDButtonViewModel
import com.mirego.trikot.viewmodels.declarative.content.VMDTextContent
import com.mirego.trikot.viewmodels.declarative.viewmodel.VMDViewModelImpl
import com.mirego.trikot.viewmodels.declarative.viewmodel.text
import kotlinx.coroutines.CoroutineScope

class EmptyViewModelImpl(
    icon: PilotImageResource = SharedImageResource.emptyPageIcon,
    title: String,
    message: String,
    override val actionButton: VMDButtonViewModel<VMDTextContent>?,
    override val secondaryActionButton: VMDButtonViewModel<VMDTextContent>? = null,
    coroutineScope: CoroutineScope
) : EmptyViewModel, VMDViewModelImpl(coroutineScope) {

    override val icon = PilotLocalImageContent(icon)

    override val title = text(title)

    override val message = text(message)
}
