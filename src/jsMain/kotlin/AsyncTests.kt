package com.tomuvak.testing.coroutines

import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.asPromise
import kotlinx.coroutines.async

private val testScope = CoroutineScope(CoroutineName("test-scope"))

actual fun asyncTest(block: suspend CoroutineScope.() -> Unit): dynamic = testScope.async { block() }.asPromise()
