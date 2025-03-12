package com.mirego.kmp.boilerplate.viewmodel.common

import com.mirego.pilot.components.PilotButton
import com.mirego.pilot.components.content.PilotLocalImageContent

interface ErrorViewModel {
    val icon: PilotLocalImageContent
    val title: String
    val message: String
    val retryButton: PilotButton<String>
}
