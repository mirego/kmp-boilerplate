package com.mirego.kmp.boilerplate.app.ui.application

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
//import com.mirego.kmp.boilerplate.app.ui.preview.PreviewProvider
import com.mirego.kmp.boilerplate.app.ui.root.RootView
import com.mirego.kmp.boilerplate.app.ui.theme.Theme
import com.mirego.kmp.boilerplate.viewmodel.application.ApplicationViewModel

@Composable
fun ApplicationView(applicationViewModel: ApplicationViewModel) {
    Theme {
        RootView(rootViewModel = applicationViewModel.rootViewModel, navigationManager = applicationViewModel.navigationManager)
    }
}

//@Preview
//@Composable
//fun PreviewApplicationView() {
//    PreviewProvider {
//        ApplicationView(applicationViewModel = it.createApplication())
//    }
//}
