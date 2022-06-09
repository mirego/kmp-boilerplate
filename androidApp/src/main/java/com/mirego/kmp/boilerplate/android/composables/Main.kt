package com.mirego.kmp.boilerplate.android.composables

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.mirego.kmp.boilerplate.android.composables.example.Example
import com.mirego.kmp.boilerplate.android.composables.home.Home
import com.mirego.kmp.boilerplate.presentation.routing.MainRouter
import com.mirego.kmp.boilerplate.presentation.routing.Router
import com.mirego.kmp.boilerplate.presentation.routing.Screen
import com.mirego.kmp.boilerplate.presentation.viewmodel.MobileViewModelFactory
import com.mirego.kmp.boilerplate.presentation.viewmodel.ViewModelFactory

@Composable
fun Main(
    viewModelFactory: ViewModelFactory = MobileViewModelFactory,
    router: Router = MainRouter
) {
    val screen: Screen by router.screen.collectAsState(initial = Screen.Home)

    screen.let {
        when (it) {
            Screen.Home -> Home(viewModel = viewModelFactory.homeViewModel)
            is Screen.Example -> Example(viewModel = viewModelFactory.exampleViewModel(it.origin))
        }
    }
}
