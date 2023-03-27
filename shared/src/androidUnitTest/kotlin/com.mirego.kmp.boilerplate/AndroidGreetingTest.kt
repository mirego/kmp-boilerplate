package com.mirego.kmp.boilerplate

import kotlin.test.Test
import kotlin.test.assertTrue
import kotlinx.coroutines.flow.single
import kotlinx.coroutines.test.runTest

class AndroidGreetingTest {

    @Test
    fun testExample() = runTest {
        assertTrue(Greeting().greeting().single().contains("Android"), "Check Android is mentioned")
    }
}
