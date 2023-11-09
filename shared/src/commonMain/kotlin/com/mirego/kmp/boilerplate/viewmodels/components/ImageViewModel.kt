package com.mirego.kmp.boilerplate.viewmodels.components

import kotlinx.coroutines.flow.StateFlow

interface ImageViewModel {
    val state: StateFlow<State>

    class State(
        val url: String,
        val contentDescription: String?
    )
    /*{
        class Local(
            val resource: ImageResource,
            contentDescription: String? = null
        ) : State(contentDescription)

        class Remote(
            val url: String,
            val placeholder: ImageResource? = null,
            contentDescription: String? = null
        ) : State(contentDescription)
    }*/
}
