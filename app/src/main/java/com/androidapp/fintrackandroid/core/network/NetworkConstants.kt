package com.androidapp.fintrackandroid.core.network

object NetworkConstants {

    // Android Emulator → local backend
    const val BASE_URL = "http://10.0.2.2:3500/"

    // HTTP headers
    const val AUTHORIZATION_HEADER = "Authorization"
    const val CONTENT_TYPE_HEADER = "Content-Type"
    const val ACCEPT_HEADER = "Accept"

    // Header values
    const val BEARER_PREFIX = "Bearer "
    const val APPLICATION_JSON = "application/json"

    // Used for endpoints that do not require authentication
    const val NO_AUTH_HEADER = "No-Auth"

    // Network timeout in seconds
    const val CONNECT_TIMEOUT = 30L
    const val READ_TIMEOUT = 30L
    const val WRITE_TIMEOUT = 30L
}