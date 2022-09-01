package com.tomuvak.testing.coroutines

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.runBlocking

actual fun asyncTest(block: suspend CoroutineScope.() -> Unit): Unit = runBlocking(block=block)
