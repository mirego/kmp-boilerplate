package com.mirego.kmp.boilerplate.android.composables

import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.UiComposable
import com.mirego.kmp.boilerplate.presentation.viewmodel.RoutableViewModel

@Composable
fun Routable(
    routableViewModel: RoutableViewModel,
    content: @Composable @UiComposable () -> Unit
) {
    val backEnabled: Boolean by routableViewModel.backEnabled.collectAsState(false)

    BackHandler(enabled = backEnabled) {
        routableViewModel.onBackRequested()
    }

    content()
}
