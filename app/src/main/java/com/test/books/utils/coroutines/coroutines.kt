package com.test.books.utils.coroutines

import com.test.books.App
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

private fun createCoroutineExceptionHandler(
    exceptionListener: ((Throwable) -> Unit)? = null,
    gradualExceptionListener: (suspend CoroutineScope.(Throwable) -> Unit)? = null,
): CoroutineExceptionHandler =
    CoroutineExceptionHandler { _, t ->
        if (App.debug) t.printStackTrace()
        exceptionListener?.invoke(t) ?: ioThread {
            gradualExceptionListener?.invoke(this, t)
        }
    }

private val ioJob: Job
    get() = Job()

private val ioCoroutineContext: CoroutineContext
    get() = ioJob + Dispatchers.IO + CoroutineName("IO Coroutine")

private fun createIOCoroutineScope(
    exceptionListener: ((Throwable) -> Unit)? = null,
    gradualExceptionListener: (suspend CoroutineScope.(Throwable) -> Unit)? = null,
): CoroutineScope {
    val coroutineContext = ioCoroutineContext + createCoroutineExceptionHandler(
        exceptionListener,
        gradualExceptionListener
    )

    return CoroutineScope(coroutineContext)
}

fun ioThread(
    exceptionListener: ((Throwable) -> Unit)? = null,
    onCoroutine: suspend CoroutineScope.() -> Unit
) {
    createIOCoroutineScope(exceptionListener = exceptionListener).launch {
        onCoroutine()
    }
}

fun gradualIoThread(
    exceptionListener: (suspend CoroutineScope.(Throwable) -> Unit)? = null,
    onCoroutine: suspend CoroutineScope.() -> Unit
) {
    createIOCoroutineScope(gradualExceptionListener = exceptionListener).launch {
        onCoroutine()
    }
}