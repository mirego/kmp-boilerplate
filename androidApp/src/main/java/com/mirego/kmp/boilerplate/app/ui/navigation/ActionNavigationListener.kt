package com.mirego.kmp.boilerplate.app.ui.navigation

import com.mirego.kmp.boilerplate.viewmodel.navigation.NavigationAction
import com.mirego.pilot.navigation.PilotActionNavigationListener

class ActionNavigationListener : PilotActionNavigationListener<NavigationAction>() {
    override fun handleAction(action: NavigationAction) {}
}
