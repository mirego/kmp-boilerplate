package com.mirego.kmp.boilerplate.viewmodel.navigation

import com.mirego.kmp.boilerplate.viewmodel.dialog.DialogNavigationData
import com.mirego.kmp.boilerplate.viewmodel.navigation.DemoNavigationRouteName.*
import com.mirego.kmp.boilerplate.viewmodel.navigation.vmd.UUIDGenerator
import com.mirego.kmp.boilerplate.viewmodel.navigation.vmd.VMDNavigationRoute
import com.mirego.trikot.foundation.concurrent.AtomicReference
import com.mirego.trikot.foundation.concurrent.setOrThrow

enum class DemoNavigationRouteName {
    SCREEN1,
    SCREEN2,
    SCREEN3,
    DIALOG
}

sealed interface DemoNavigationRoute : VMDNavigationRoute {
    class Screen1 : DemoNavigationRoute {
        override val name: String = SCREEN1.name
        override val uniqueId: String = UUIDGenerator.uuid()
    }

    class Screen2 : DemoNavigationRoute {
        override val name: String = SCREEN2.name
        override val uniqueId: String = UUIDGenerator.uuid()
    }

    class Screen3 : DemoNavigationRoute {
        override val name: String = SCREEN3.name
        override val uniqueId: String = UUIDGenerator.uuid()
    }

    data class Dialog(
        val navigationData: DialogNavigationData
    ) : DemoNavigationRoute {
        override val name: String = DIALOG.name
        override val uniqueId: String = UUIDGenerator.uuid()
    }
}
