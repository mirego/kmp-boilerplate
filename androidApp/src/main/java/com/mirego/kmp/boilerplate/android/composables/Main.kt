package com.mirego.kmp.boilerplate.android.composables

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.mirego.kmp.boilerplate.routing.MainRouter
import com.mirego.kmp.boilerplate.routing.Screen

@Composable
fun Main() {
    val screen: Screen by MainRouter.screen.collectAsState(initial = Screen.Home)

    when (screen) {
        is Screen.Home -> Home()
    }
}
