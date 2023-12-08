package com.mirego.kmp.boilerplate.viewmodel.navigation.vmd

import platform.Foundation.NSUUID

actual object UUIDGenerator {
    actual fun uuid(): String = NSUUID().UUIDString()
}
