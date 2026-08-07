package com.androidapp.fintrackandroid.di

import com.androidapp.fintrackandroid.BuildConfig
import com.androidapp.fintrackandroid.core.network.AuthInterceptor
import com.androidapp.fintrackandroid.core.network.FinTrackApiService
import com.androidapp.fintrackandroid.core.network.NetworkConstants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideJson(): Json {
        return Json {
            ignoreUnknownKeys = true
            explicitNulls = false
            encodeDefaults = true
            coerceInputValues = true
        }
    }

    @Provides
    @Singleton
    fun provideLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply {
            redactHeader(
                NetworkConstants.AUTHORIZATION_HEADER
            )

            level = if (BuildConfig.DEBUG) {
                HttpLoggingInterceptor.Level.BASIC
            } else {
                HttpLoggingInterceptor.Level.NONE
            }
        }
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(
        authInterceptor: AuthInterceptor,
        loggingInterceptor: HttpLoggingInterceptor
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(authInterceptor)
            .addInterceptor(loggingInterceptor)
            .connectTimeout(
                NetworkConstants.CONNECT_TIMEOUT,
                TimeUnit.SECONDS
            )
            .readTimeout(
                NetworkConstants.READ_TIMEOUT,
                TimeUnit.SECONDS
            )
            .writeTimeout(
                NetworkConstants.WRITE_TIMEOUT,
                TimeUnit.SECONDS
            )
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(
        json: Json,
        okHttpClient: OkHttpClient
    ): Retrofit {
        val contentType = NetworkConstants.APPLICATION_JSON.toMediaType()

        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(json.asConverterFactory(contentType))
            .build()
    }

    @Provides
    @Singleton
    fun provideFinTrackApiService(
        retrofit: Retrofit
    ): FinTrackApiService {
        return retrofit.create(FinTrackApiService::class.java)
    }
}