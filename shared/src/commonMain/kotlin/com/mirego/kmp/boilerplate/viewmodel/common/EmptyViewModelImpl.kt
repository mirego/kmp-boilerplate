package com.mirego.kmp.boilerplate.viewmodel.common

import com.mirego.trikot.viewmodels.declarative.components.VMDButtonViewModel
import com.mirego.trikot.viewmodels.declarative.content.VMDTextContent
import com.mirego.trikot.viewmodels.declarative.properties.VMDImageResource
import com.mirego.trikot.viewmodels.declarative.viewmodel.VMDViewModelImpl
import com.mirego.trikot.viewmodels.declarative.viewmodel.localImage
import com.mirego.trikot.viewmodels.declarative.viewmodel.text
import kotlinx.coroutines.CoroutineScope

class EmptyViewModelImpl(
    icon: VMDImageResource = SharedImageResource.emptyPageIcon,
    title: String,
    message: String,
    override val actionButton: VMDButtonViewModel<VMDTextContent>?,
    override val secondaryActionButton: VMDButtonViewModel<VMDTextContent>? = null,
    coroutineScope: CoroutineScope
) : EmptyViewModel, VMDViewModelImpl(coroutineScope) {

    override val icon = localImage(icon)

    override val title = text(title)

    override val message = text(message)
}
