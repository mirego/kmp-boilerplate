package com.mirego.kmp.boilerplate.app.filesystem

import okio.FileSystem

expect object NativeFileSystem {
    val fileSystem: FileSystem
}
