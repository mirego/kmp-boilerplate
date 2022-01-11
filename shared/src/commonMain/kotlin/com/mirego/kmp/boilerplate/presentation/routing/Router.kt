package com.mirego.kmp.boilerplate.presentation.routing

import com.mirego.kmp.boilerplate.utils.CFlow
import com.mirego.kmp.boilerplate.utils.wrap
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.map

interface Router {
    val screen: CFlow<Screen>

    fun push(screen: Screen)
    fun pop()
}

object MainRouter : Router {

    private val _screens = MutableStateFlow<List<Screen>>(
        listOf(Screen.Home)
    )

    override val screen = _screens.map { it.last() }.wrap()

    override fun push(screen: Screen) {
        _screens.value += screen
    }

    override fun pop() {
        _screens.value = _screens.value.dropLast(1)
    }
}
