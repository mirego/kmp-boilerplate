package com.mirego.kmp.boilerplate.viewmodel.navigation

import com.mirego.kmp.boilerplate.viewmodel.dialog.DialogNavigationData
import com.mirego.kmp.boilerplate.viewmodel.navigation.vmd.VMDNavigationRoute
import com.mirego.trikot.foundation.concurrent.AtomicReference
import com.mirego.trikot.foundation.concurrent.setOrThrow

sealed interface DemoNavigationRoute : VMDNavigationRoute {
    class Screen1: DemoNavigationRoute {
        companion object {
            const val NAME = "Screen1"
        }

        override val name: String = NAME
        override val uniqueId: String = UniqueIdGenerator.generateUniqueId()
    }

    class Screen2 : DemoNavigationRoute {
        companion object {
            const val NAME = "Screen2"
        }

        override val name: String = NAME
        override val uniqueId: String = UniqueIdGenerator.generateUniqueId()
    }

    class Screen3: DemoNavigationRoute {
        companion object {
            const val NAME = "Screen3"
        }

        override val name: String = NAME
        override val uniqueId: String = UniqueIdGenerator.generateUniqueId()
    }

    data class Dialog(
        val navigationData: DialogNavigationData
    ) : DemoNavigationRoute {
        companion object {
            const val NAME = "Dialog"
        }

        override val name: String = NAME
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
