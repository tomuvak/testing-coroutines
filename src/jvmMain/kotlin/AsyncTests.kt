package com.tomuvak.testing.coroutines

import kotlinx.coroutines.runBlocking

actual fun asyncTest(block: suspend () -> Unit): Unit = runBlocking { block() }
