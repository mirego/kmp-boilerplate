package com.mirego.kmp.boilerplate.viewmodel.application

import com.mirego.kmp.boilerplate.viewmodel.navigation.DemoNavigationManager
import com.mirego.kmp.boilerplate.viewmodel.tab.Tab1ViewModel
import com.mirego.kmp.boilerplate.viewmodel.tab.Tab2ViewModel
import com.mirego.trikot.viewmodels.declarative.viewmodel.VMDViewModel

interface ApplicationViewModel : VMDViewModel {
    val navigationManager: DemoNavigationManager

    val tab1ViewModel: Tab1ViewModel
    val tab2ViewModel: Tab2ViewModel
}

/*
    - Créer 2 tabs dans le ApplicationViewModel
    - Chaque tab à de l'intra tab navigation
    - Chaque tap peut affihcer du modal
    - Chaque intra tab navigation push pusher 2ieme niveau et modal
    - Un modal push doit faire un push et ce push doit pouvoir dismiss le modal direct
    - Deeplink d'un service peut afficher de quoi de modal !
    - Penser au "débounce" pour ne pas faire 2 push ou 2 modal en même temps
 */
