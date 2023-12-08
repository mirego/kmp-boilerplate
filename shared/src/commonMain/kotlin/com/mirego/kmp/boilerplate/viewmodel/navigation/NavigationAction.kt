package com.mirego.kmp.boilerplate.viewmodel.navigation

sealed interface NavigationAction {
    data class ExternalUrl(val url: String) : NavigationAction
    data object OpenSettings : NavigationAction
}
