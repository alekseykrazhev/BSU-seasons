package com.example.android.lab11.quiz_application.data.common.di

import android.content.Context
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.example.android.lab11.quiz_application.data.common.ConnectivityInterceptor
import com.example.android.lab11.quiz_application.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@ExperimentalSerializationApi
@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Singleton
    @Provides
    fun provideRetrofit(contentType: MediaType, json: Json, client: OkHttpClient): Retrofit =
        Retrofit.Builder().apply {
            client(client)
            addConverterFactory(json.asConverterFactory(contentType))
            baseUrl(Constants.BASE_URL)
        }.build()

    @Provides
    @Singleton
    fun provideOkHttpClient(interceptor: ConnectivityInterceptor): OkHttpClient =
        OkHttpClient.Builder().apply {
            readTimeout(30, TimeUnit.SECONDS)
            connectTimeout(60, TimeUnit.SECONDS)
            addInterceptor(interceptor)
        }.build()

    @Provides
    fun provideContentType(): MediaType = "application/json".toMediaType()

    @Provides
    fun provideJson(): Json = Json { ignoreUnknownKeys = true }

    @Provides
    fun provideConnectivityInterceptor(
        @ApplicationContext context: Context
    ): ConnectivityInterceptor = ConnectivityInterceptor(context)
}