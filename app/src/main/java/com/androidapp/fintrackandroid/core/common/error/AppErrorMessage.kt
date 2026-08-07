package com.androidapp.fintrackandroid.core.common.error

import androidx.annotation.StringRes
import com.androidapp.fintrackandroid.R

@StringRes
fun AppError.toUserMessageRes(): Int {
    return when (this) {
        AppError.BadRequest -> R.string.error_bad_request
        AppError.Unauthorized -> R.string.error_unauthorized
        AppError.NotFound -> R.string.error_not_found
        AppError.Server -> R.string.error_server
        AppError.Network -> R.string.error_network
        AppError.Timeout -> R.string.error_timeout
        is AppError.Http -> R.string.error_http
        is AppError.Unknown -> R.string.error_unknown
    }
}