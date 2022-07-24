package com.tomuvak.testing.coroutines

import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.test.Test
import kotlin.test.assertEquals

class AsyncTestsTest {
    @Test fun compilesAndRunsAsyncTest() = asyncTest {}

    @Test fun executesLaunchedCoroutineAsync() = asyncTest {
        var value = "initial"
        launch {
            delay(100)
            value = "final"
        }
        delay(50)
        assertEquals("initial", value)
        delay(100)
        assertEquals("final", value)
    }
}
