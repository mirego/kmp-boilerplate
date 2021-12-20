package com.mirego.kmp.boilerplate

import kotlinx.coroutines.flow.single
import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.test.assertTrue

class AndroidGreetingTest {

    @Test
    fun testExample() = runTest {
        assertTrue(Greeting().greeting().single().contains("Android"), "Check Android is mentioned")
    }
}
