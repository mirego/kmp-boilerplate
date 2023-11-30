package com.mirego.kmp.boilerplate.viewmodel.navigation

import com.mirego.kmp.boilerplate.viewmodel.dialog.DialogNavigationData
import com.mirego.kmp.boilerplate.viewmodel.navigation.vmd.VMDNavigationRoute
import com.mirego.trikot.foundation.concurrent.AtomicReference
import com.mirego.trikot.foundation.concurrent.setOrThrow

sealed interface DemoNavigationRoute : VMDNavigationRoute {

    data class Root(
        override val uniqueId: String = UniqueIdGenerator.generateUniqueId()
    ) : DemoNavigationRoute {
        override val name: String = "Root"
    }

    data class Tab1(
        override val uniqueId: String = UniqueIdGenerator.generateUniqueId()
    ) : DemoNavigationRoute {
        override val name: String = "Tab1"
    }

    data class Tab2(
        override val uniqueId: String = UniqueIdGenerator.generateUniqueId()
    ) : DemoNavigationRoute {
        override val name: String = "Tab2"
    }

    data class Screen1(
        override val uniqueId: String = UniqueIdGenerator.generateUniqueId()
    ) : DemoNavigationRoute {
        override val name: String = "Screen1"
    }

    data class Screen2(
        override val uniqueId: String = UniqueIdGenerator.generateUniqueId()
    ) : DemoNavigationRoute {
        override val name: String = "Screen2"
    }

    data class Screen3(
        override val uniqueId: String = UniqueIdGenerator.generateUniqueId()
    ) : DemoNavigationRoute {
        override val name: String = "Screen3"
    }

    data class Dialog(
        val navigationData: DialogNavigationData,
        override val uniqueId: String = UniqueIdGenerator.generateUniqueId()
    ) : DemoNavigationRoute {
        override val name: String = "Dialog"
    }

    data class ExternalUrl(
        val url: String,
        override val uniqueId: String = UniqueIdGenerator.generateUniqueId()
    ) : DemoNavigationRoute {
        override val name: String = "ExternalUrl"
    }
}

private object UniqueIdGenerator {
    private val id = AtomicReference<Long>(0)
    fun generateUniqueId(): String {
        id.setOrThrow(id.value + 1)
        return id.value.toString()
    }
}
