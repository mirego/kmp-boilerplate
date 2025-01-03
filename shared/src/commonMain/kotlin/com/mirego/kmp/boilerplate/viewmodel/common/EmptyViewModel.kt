package com.mirego.kmp.boilerplate.viewmodel.common

import com.mirego.pilot.components.PilotButton
import com.mirego.pilot.components.content.PilotLocalImageContent

interface EmptyViewModel {
    val icon: PilotLocalImageContent
    val title: String
    val message: String
    val actionButton: PilotButton<String>?
    val secondaryActionButton: PilotButton<String>?
}
