package com.test.books.data.model.result

import com.test.books.App
import com.test.books.utils.coroutines.gradualIoThread
import com.test.books.utils.coroutines.ioThread
import com.test.books.utils.exceptions.NoDataException
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

sealed class ResultOf<out T> {
    data class Success<out T>(val data: T) : ResultOf<T>()
    class Failure(val t: Throwable) : ResultOf<Nothing>()

    inline fun <K> mapResult(crossinline onChange: (T) -> K): ResultOf<K> =
        if (this is Success) Success(onChange(data)) else {
            val failureResult = this as Failure
            Failure(failureResult.t)
        }

    companion object {
        inline fun <T> createFlowDefault(
            crossinline onDebug: () -> T,
            crossinline onLoaded: CoroutineScope.() -> T,
            crossinline onDatabase: CoroutineScope.() -> T
        ): Flow<ResultOf<T>> = flow {
            ioThread(exceptionListener = {
                gradualIoThread(exceptionListener = {
                    emit(Failure(NoDataException(it.message)))
                }) { emit(Success(onDatabase())) }
            }) {
                emit(Success(if (App.debug) onDebug() else onLoaded()))
            }
        }

        inline fun <T> createFlow(
            crossinline onLoaded: suspend () -> T
        ): Flow<ResultOf<T>> = flow<ResultOf<T>> {
            emit(Success(onLoaded()))
        }.catch {
            emit(Failure(it))
        }.flowOn(Dispatchers.IO)
    }

    suspend inline fun onSuccess(crossinline callback: suspend (T) -> Unit): ResultOf<T> =
        this.also { if (it is Success) callback(it.data) }

    suspend inline fun onFailure(crossinline callback: suspend (Throwable) -> Unit): ResultOf<T> =
        this.also { if (it is Failure) callback(it.t) }


}






