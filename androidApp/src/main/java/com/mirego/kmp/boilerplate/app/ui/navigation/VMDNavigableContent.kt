package com.mirego.kmp.boilerplate.app.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.mirego.kmp.mirego.trikot.viewmodels.declarative.navigation.VMDNavigationRoute
import com.mirego.kmp.mirego.trikot.viewmodels.declarative.navigation.VMDNavigationViewModel

/**
 * When using VMDNavigationView, wrap the different navigation destinations in a VMDNavigableContent
 *
 * @param navigationViewModel The VMDNavigationViewModel
 * @param content The wrapped navigation content. The block will receive the navigationRoute, as well as a flag to indicate if the composition is done in the context of an exit transition.
 */
@Composable
fun <T : VMDNavigationRoute> VMDNavigableContent(
    navigationViewModel: VMDNavigationViewModel<T>,
    content: @Composable (navigationRoute: T) -> Unit
) {
    val route: T? by remember(key1 = navigationViewModel) {
        mutableStateOf(navigationViewModel.navigationRoute)
    }
    val navigationRoute = route ?: return
    content(navigationRoute)
}
