package com.mirego.kmp.boilerplate.app.utils

import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.addressOf
import kotlinx.cinterop.usePinned
import platform.Foundation.NSData
import platform.Foundation.create
import platform.posix.memcpy

object ByteArrayNativeUtils {
    @OptIn(ExperimentalForeignApi::class)
    @ExperimentalUnsignedTypes
    fun convert(data: NSData): ByteArray {
        return data.bytes?.let { bytes ->
            ByteArray(data.length.toInt()).apply {
                usePinned { pinned ->
                    memcpy(pinned.addressOf(0), bytes, data.length)
                }
            }
        } ?: ByteArray(0)
    }

    @OptIn(ExperimentalForeignApi::class)
    @ExperimentalUnsignedTypes
    fun convert(byteArray: ByteArray): NSData {
        return byteArray.usePinned {
            NSData.create(
                bytes = it.addressOf(0),
                length = byteArray.size.toULong()
            )
        }
    }
}
