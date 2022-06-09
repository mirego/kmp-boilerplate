package com.mirego.kmp.boilerplate.presentation.viewmodel

import com.mirego.kmp.boilerplate.presentation.routing.MainRouter
import com.mirego.kmp.boilerplate.presentation.routing.Router
import com.mirego.kmp.boilerplate.presentation.viewmodel.example.ExampleViewModel
import com.mirego.kmp.boilerplate.presentation.viewmodel.example.ExampleViewModelImpl
import com.mirego.kmp.boilerplate.presentation.viewmodel.home.HomeViewModel
import com.mirego.kmp.boilerplate.presentation.viewmodel.home.HomeViewModelImpl

interface ViewModelFactory {
    val homeViewModel: HomeViewModel
    fun exampleViewModel(origin: String): ExampleViewModel
}

object MobileViewModelFactory : ViewModelFactory {

    private val router: Router = MainRouter

    override val homeViewModel: HomeViewModel
        get() = HomeViewModelImpl(router)

    override fun exampleViewModel(origin: String) =
        ExampleViewModelImpl(router, origin)
}
