package com.frommetoyou.moviesbuyer.presentation.di

import com.frommetoyou.moviesbuyer.data.util.CoroutinesDispatcherProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class WrappersModule {
    @Named("provideMoviesCoroutinesDispatchers")
    @Singleton
    @Provides
    fun provideMoviesCoroutinesDispatchers(): CoroutinesDispatcherProvider {
        return CoroutinesDispatcherProvider()
    }
}