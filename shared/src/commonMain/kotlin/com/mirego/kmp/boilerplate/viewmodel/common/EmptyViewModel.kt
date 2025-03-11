package com.mirego.kmp.boilerplate.viewmodel.common

import com.mirego.pilot.components.PilotButton
import com.mirego.pilot.components.content.PilotLocalImageContent
import com.mirego.trikot.viewmodels.declarative.viewmodel.VMDViewModel

interface EmptyViewModel : VMDViewModel {
    val icon: PilotLocalImageContent
    val title: String
    val message: String
    val actionButton: PilotButton<String>?
    val secondaryActionButton: PilotButton<String>?
}
