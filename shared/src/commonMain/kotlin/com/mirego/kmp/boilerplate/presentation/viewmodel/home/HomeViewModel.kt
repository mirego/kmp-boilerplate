package com.mirego.kmp.boilerplate.presentation.viewmodel.home

import com.mirego.kmp.boilerplate.Greeting
import com.mirego.kmp.boilerplate.presentation.routing.MainRouter
import com.mirego.kmp.boilerplate.presentation.routing.Screen
import com.mirego.kmp.boilerplate.utils.CFlow
import com.mirego.kmp.boilerplate.utils.wrap
import kotlinx.coroutines.flow.flowOf

interface HomeViewModel {
    val greetingMessage: CFlow<String>

    val buttonText: CFlow<String>
    fun onButtonClick()

    object Preview : HomeViewModel {
        override val greetingMessage = flowOf("Hello!").wrap()
        override val buttonText = flowOf("Click here!").wrap()
        override fun onButtonClick() = Unit
    }
}

class HomeViewModelImpl(
    greeting: Greeting = Greeting()
) : HomeViewModel {
    override val greetingMessage = greeting.greeting().wrap()

    override val buttonText = flowOf("Click me!").wrap()

    override fun onButtonClick() {
        MainRouter.push(Screen.Example(origin = "Home"))
    }
}
