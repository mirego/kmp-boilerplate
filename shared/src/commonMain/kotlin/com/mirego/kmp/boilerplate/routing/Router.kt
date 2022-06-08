package com.mirego.kmp.boilerplate.routing

import com.mirego.kmp.boilerplate.utils.CFlow
import com.mirego.kmp.boilerplate.utils.wrap
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.map

interface Router {
    val screen: CFlow<Screen>

    fun push(screen: Screen)
}

object MainRouter : Router {

    private val _screens = MutableStateFlow<List<Screen>>(
        listOf(Screen.Home)
    )

    override val screen = _screens.map { it.last() }.wrap()

    override fun push(screen: Screen) {
        _screens.value += screen
    }
}
