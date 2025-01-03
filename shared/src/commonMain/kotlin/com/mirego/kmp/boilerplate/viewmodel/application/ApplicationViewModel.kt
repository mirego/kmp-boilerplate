package com.mirego.kmp.boilerplate.viewmodel.application

import com.mirego.kmp.boilerplate.viewmodel.navigation.NavigationManager
import com.mirego.kmp.boilerplate.viewmodel.root.RootViewModel
import com.mirego.pilot.viewmodel.PilotViewModel

abstract class ApplicationViewModel : PilotViewModel() {
    abstract val navigationManager: NavigationManager
    abstract val rootViewModel: RootViewModel
}
