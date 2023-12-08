package com.mirego.kmp.boilerplate.viewmodel.navigation.vmd

import java.util.UUID

actual object UUIDGenerator {
    actual fun uuid(): String = UUID.randomUUID().toString()
}
