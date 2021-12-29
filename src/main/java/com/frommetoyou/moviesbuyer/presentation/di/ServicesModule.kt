package com.frommetoyou.moviesbuyer.presentation.di

import com.frommetoyou.moviesbuyer.data.services.api.ComicApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

private const val DEV_URL  = "https://gateway.marvel.com/v1/"
const val BASE_URL = DEV_URL

@Module
@InstallIn(SingletonComponent::class)
class ServicesModule {
    @Singleton
    @Provides
    fun provideLoginAPI(httpClient: OkHttpClient): ComicApiService {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(httpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ComicApiService::class.java)
    }

    @Singleton
    @Provides
    fun provideOkHttpClient(): OkHttpClient {
        val interceptor = HttpLoggingInterceptor()
        val loggingInterceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        return OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .addInterceptor(loggingInterceptor)
            .build()
    }
}