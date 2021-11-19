package com.mirego.kmp.boilerplate

import kotlin.test.Test
import kotlin.test.assertTrue

class JsGreetingTest {

    @Test
    fun testExample() {
        assertTrue(Greeting().greeting().contains("HeadlessChrome"), "Check userAgent is mentioned")
    }
}
