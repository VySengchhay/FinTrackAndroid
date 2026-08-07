package com.androidapp.fintrackandroid.core.common.error

import retrofit2.HttpException
import java.io.IOException
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import java.util.concurrent.CancellationException

fun Throwable.toAppError(): AppError {
    if (this is CancellationException) {
        throw this
    }

    return when (this) {
        is HttpException -> toHttpAppError()

        is SocketTimeoutException -> AppError.Timeout

        is UnknownHostException,
        is ConnectException,
        is IOException -> AppError.Network

        else -> AppError.Unknown(this)
    }
}

private fun HttpException.toHttpAppError(): AppError {
    return when (code()) {
        400 -> AppError.BadRequest
        401 -> AppError.Unauthorized
        404 -> AppError.NotFound
        in 500..599 -> AppError.Server
        else -> AppError.Http(statusCode = code())
    }
}