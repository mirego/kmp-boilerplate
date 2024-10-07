package com.mirego.kmp.boilerplate.viewmodel.common

import com.mirego.kmp.boilerplate.localization.KWordTranslation
import com.mirego.pilot.components.PilotImageResource
import com.mirego.pilot.components.content.PilotLocalImageContent
import com.mirego.trikot.kword.I18N
import com.mirego.trikot.viewmodels.declarative.viewmodel.VMDViewModelImpl
import com.mirego.trikot.viewmodels.declarative.viewmodel.buttonWithText
import com.mirego.trikot.viewmodels.declarative.viewmodel.text
import kotlinx.coroutines.CoroutineScope

class ErrorViewModelImpl(
    icon: PilotImageResource,
    title: String,
    message: String,
    retryLabel: String,
    retryAction: () -> Unit,
    coroutineScope: CoroutineScope
) : ErrorViewModel, VMDViewModelImpl(coroutineScope) {

    companion object {
        fun build(i18N: I18N, titleKey: KWordTranslation, messageKey: KWordTranslation, coroutineScope: CoroutineScope, retryAction: () -> Unit) = ErrorViewModelImpl(
            icon = SharedImageResource.errorPageIcon,
            title = i18N[titleKey],
            message = i18N[messageKey],
            retryLabel = i18N[KWordTranslation.GENERIC_RETRY],
            retryAction = retryAction,
            coroutineScope = coroutineScope
        )
    }

    override val icon = PilotLocalImageContent(icon)

    override val title = text(title)

    override val message = text(message)

    override val retryButton = buttonWithText(retryLabel, retryAction)
}
