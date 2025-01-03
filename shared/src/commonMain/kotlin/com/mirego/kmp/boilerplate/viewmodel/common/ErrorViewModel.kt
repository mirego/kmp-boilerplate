package com.mirego.kmp.boilerplate.viewmodel.common

import com.mirego.pilot.components.PilotButton
import com.mirego.pilot.components.content.PilotLocalImageContent
import com.mirego.pilot.viewmodel.PilotViewModel

abstract class ErrorViewModel : PilotViewModel() {
    abstract val icon: PilotLocalImageContent
    abstract val title: String
    abstract val message: String
    abstract val retryButton: PilotButton<String>
}
