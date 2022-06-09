package com.mirego.kmp.boilerplate.presentation.viewmodel

import kotlinx.coroutines.flow.Flow

interface RoutableViewModel {
    val backEnabled: Flow<Boolean>
    fun onBackRequested()
}
