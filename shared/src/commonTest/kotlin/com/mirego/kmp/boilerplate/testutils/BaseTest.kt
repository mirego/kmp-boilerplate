package com.mirego.kmp.boilerplate.testutils

import com.mirego.trikot.kword.I18N
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.setMain

open class BaseTest {
    val i18N: I18N = I18NMock(withArgs = true)
    val testCoroutineScope = CoroutineScope(Dispatchers.Unconfined)

    init {
        Dispatchers.setMain(UnconfinedTestDispatcher())
    }
}
