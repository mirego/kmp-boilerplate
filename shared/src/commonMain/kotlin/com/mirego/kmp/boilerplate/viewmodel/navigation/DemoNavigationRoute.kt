package com.mirego.kmp.boilerplate.viewmodel.navigation

import com.mirego.kmp.boilerplate.viewmodel.dialog.DialogNavigationData
import com.mirego.kmp.boilerplate.viewmodel.navigation.vmd.VMDNavigationRoute
import com.mirego.trikot.foundation.concurrent.AtomicReference
import com.mirego.trikot.foundation.concurrent.setOrThrow

sealed interface DemoNavigationRoute : VMDNavigationRoute {

    data object Root : DemoNavigationRoute {
        override val name: String = "Root"
        override val uniqueId: String = UniqueIdGenerator.generateUniqueId()
    }

    data object Tab1 : DemoNavigationRoute {
        override val name: String = "Tab1"
        override val uniqueId: String = UniqueIdGenerator.generateUniqueId()
    }

    data object Tab2 : DemoNavigationRoute {
        override val name: String = "Tab2"
        override val uniqueId: String = UniqueIdGenerator.generateUniqueId()
    }

    data object Screen1 : DemoNavigationRoute {
        override val name: String = "Screen1"
        override val uniqueId: String = UniqueIdGenerator.generateUniqueId()
    }

    data object Screen2 : DemoNavigationRoute {
        override val name: String = "Screen2"
        override val uniqueId: String = UniqueIdGenerator.generateUniqueId()
    }

    data object Screen3 : DemoNavigationRoute {
        override val name: String = "Screen3"
        override val uniqueId: String = UniqueIdGenerator.generateUniqueId()
    }

    data class Dialog(
        val navigationData: DialogNavigationData
    ) : DemoNavigationRoute {
        override val name: String = "Dialog"
        override val uniqueId: String = UniqueIdGenerator.generateUniqueId()
    }

    data class ExternalUrl(val url: String) : DemoNavigationRoute {
        override val name: String = "ExternalUrl"
        override val uniqueId: String = UniqueIdGenerator.generateUniqueId()
    }
}

private object UniqueIdGenerator {
    private val id = AtomicReference<Long>(0)
    fun generateUniqueId(): String {
        id.setOrThrow(id.value + 1)
        return id.value.toString()
    }
}
