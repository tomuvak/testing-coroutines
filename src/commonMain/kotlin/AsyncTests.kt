package com.tomuvak.testing.coroutines

import kotlinx.coroutines.CoroutineScope

expect fun asyncTest(block: suspend CoroutineScope.() -> Unit)
