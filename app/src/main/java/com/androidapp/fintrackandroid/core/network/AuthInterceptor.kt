package com.androidapp.fintrackandroid.core.network

import com.androidapp.fintrackandroid.core.session.AuthSession
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthInterceptor @Inject constructor(
    private val authSession: AuthSession
) : Interceptor {

    override fun intercept(
        chain: Interceptor.Chain
    ): Response {
        val originalRequest = chain.request()

        val doesNotRequireAuthentication =
            originalRequest.header(
                NetworkConstants.NO_AUTH_HEADER
            ) != null

        val requestBuilder = originalRequest
            .newBuilder()
            .removeHeader(NetworkConstants.NO_AUTH_HEADER)

        if (doesNotRequireAuthentication) {
            return chain.proceed(requestBuilder.build())
        }

        val accessToken = authSession.getAccessToken()

        if (!accessToken.isNullOrBlank()) {
            requestBuilder.header(
                NetworkConstants.AUTHORIZATION_HEADER,
                NetworkConstants.BEARER_PREFIX + accessToken
            )
        }

        return chain.proceed(requestBuilder.build())
    }
}