package com.tomuvak.testing.coroutines

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.promise

@OptIn(DelicateCoroutinesApi::class)
actual fun asyncTest(block: suspend CoroutineScope.() -> Unit): dynamic = GlobalScope.promise(block=block)
