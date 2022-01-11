package com.mirego.kmp.boilerplate.presentation.viewmodel.example

import com.mirego.kmp.boilerplate.presentation.routing.MainRouter
import com.mirego.kmp.boilerplate.utils.CFlow
import com.mirego.kmp.boilerplate.utils.wrap
import kotlinx.coroutines.flow.flowOf

interface ExampleViewModel {
    val exampleMessage: CFlow<String>
    val backButtonText: CFlow<String>
    fun onBackButtonClick()

    object Preview : ExampleViewModel {
        override val exampleMessage = flowOf("Example text").wrap()
        override val backButtonText = flowOf("Back button text").wrap()
        override fun onBackButtonClick() = Unit
    }
}

class ExampleViewModelImpl(
    origin: String
) : ExampleViewModel {
    override val exampleMessage = flowOf("You've pushed a new Router Screen!").wrap()

    override val backButtonText = flowOf("Go back to \"$origin\" screen").wrap()

    override fun onBackButtonClick() = MainRouter.pop()
}