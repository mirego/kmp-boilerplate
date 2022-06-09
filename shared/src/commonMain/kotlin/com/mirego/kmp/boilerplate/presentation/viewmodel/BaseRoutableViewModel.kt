package com.mirego.kmp.boilerplate.presentation.viewmodel

import com.mirego.kmp.boilerplate.presentation.routing.Router
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

abstract class BaseRoutableViewModel(
    private val router: Router
) : RoutableViewModel {

    override val backEnabled: Flow<Boolean> = flowOf(true)

    override fun onBackRequested() = router.pop()

    abstract class Preview : RoutableViewModel {
        override val backEnabled = flowOf(false)
        override fun onBackRequested() = Unit
    }
}
