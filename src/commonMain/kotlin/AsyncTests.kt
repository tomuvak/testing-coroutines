package com.tomuvak.testing.coroutines

import kotlinx.coroutines.CoroutineScope

/**
 * Used to wrap the test code given in [block] – which can call `suspend` functions and use [CoroutineScope] – in a way
 * that allows it to be run as a test on the various platforms.
 *
 * Example:
 *
 * ```kotlin
 * // The following code can be used without change on all platforms.
 * @Test fun `some test function which uses coroutines / async code`() = asyncTest {
 *     // Actual test code here; coroutine functionality can be used as
 *     // this is a `suspend` block with a `CoroutineScope` receiver.
 * }
 * ```
 */
expect fun asyncTest(block: suspend CoroutineScope.() -> Unit)
