package com.mirego.kmp.boilerplate.app.filesystem

import okio.FileSystem

actual object NativeFileSystem {
    actual val fileSystem: FileSystem = FileSystem.SYSTEM
}
