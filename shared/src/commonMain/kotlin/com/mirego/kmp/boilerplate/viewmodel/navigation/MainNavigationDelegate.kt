package com.mirego.kmp.boilerplate.viewmodel.navigation

import com.mirego.kmp.boilerplate.viewmodel.projectdetails.ProjectDetailsNavigationData

interface MainNavigationDelegate {
    fun navigateToProjectDetails(navigationData: ProjectDetailsNavigationData)
}

enum class CloseActionType {
    BACK,
    COMPLETED
}
