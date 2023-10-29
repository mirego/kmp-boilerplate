@file:Suppress("VARIABLE_IN_SINGLETON_WITHOUT_THREAD_LOCAL")

package com.mirego.kmp.boilerplate.app.coroutines

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.newSingleThreadContext

object Dispatchers {
    var Default: CoroutineDispatcher = Dispatchers.Default
    var IO: CoroutineDispatcher = newSingleThreadContext("IO").limitedParallelism(100)
}
