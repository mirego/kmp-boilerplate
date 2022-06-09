package com.mirego.kmp.boilerplate.presentation.viewmodel.home

import com.mirego.kmp.boilerplate.Greeting
import com.mirego.kmp.boilerplate.presentation.routing.Router
import com.mirego.kmp.boilerplate.presentation.routing.Screen
import com.mirego.kmp.boilerplate.presentation.viewmodel.BaseRoutableViewModel
import com.mirego.kmp.boilerplate.presentation.viewmodel.RoutableViewModel
import com.mirego.kmp.boilerplate.utils.CFlow
import com.mirego.kmp.boilerplate.utils.wrap
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map

interface HomeViewModel : RoutableViewModel {
    val greetingMessage: CFlow<String>

    val buttonText: CFlow<String>
    fun onButtonClick()

    object Preview : BaseRoutableViewModel.Preview(), HomeViewModel {
        override val greetingMessage = flowOf("Hello!").wrap()
        override val buttonText = flowOf("Click here!").wrap()
        override fun onButtonClick() = Unit
    }
}

class HomeViewModelImpl(
    private val router: Router,
    greeting: Greeting = Greeting()
) : BaseRoutableViewModel(router), HomeViewModel {

    override val backEnabled: Flow<Boolean> = router.screen.map { it != Screen.Home }

    override val greetingMessage = greeting.greeting().wrap()

    override val buttonText = flowOf("Click me!").wrap()

    override fun onButtonClick() {
        router.push(Screen.Example(origin = "Home"))
    }
}
