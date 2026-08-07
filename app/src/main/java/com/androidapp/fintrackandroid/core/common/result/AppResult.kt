package com.androidapp.fintrackandroid.core.common.result

import com.androidapp.fintrackandroid.core.common.error.AppError
import com.androidapp.fintrackandroid.core.common.error.toAppError

sealed interface AppResult<out T> {
    data class Success<T>(
        val data: T
    ) : AppResult<T>
    data class Error(
        val error: AppError
    ) : AppResult<Nothing>
}

suspend fun <T> appResultOf(
    block: suspend () -> T
): AppResult<T> {
    return try {
        AppResult.Success(block())
    } catch (throwable: Throwable) {
        AppResult.Error(
            error = throwable.toAppError()
        )
    }
}