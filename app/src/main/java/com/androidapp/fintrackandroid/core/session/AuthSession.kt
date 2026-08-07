package com.androidapp.fintrackandroid.core.session

import java.util.concurrent.atomic.AtomicReference
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthSession @Inject constructor() {

    private val accessToken =
        AtomicReference<String?>(null)

    fun getAccessToken(): String? {
        return accessToken.get()
    }

    fun updateAccessToken(token: String?) {
        accessToken.set(
            token
                ?.trim()
                ?.takeIf { it.isNotEmpty() }
        )
    }

    fun clear() {
        accessToken.set(null)
    }
}