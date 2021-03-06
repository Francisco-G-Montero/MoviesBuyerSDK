package com.frommetoyou.moviesbuyer.presentation.di

import com.frommetoyou.moviesbuyer.data.repository.ComicRepositoryImpl
import com.frommetoyou.moviesbuyer.data.services.api.ComicApiService
import com.frommetoyou.moviesbuyer.data.util.CoroutinesDispatcherProvider
import com.frommetoyou.moviesbuyer.domain.repository.ComicRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Inject
import javax.inject.Named
import javax.inject.Qualifier
import javax.inject.Singleton

@ExperimentalCoroutinesApi
@Module
@InstallIn(SingletonComponent::class)
class RepositoriesModule {
    @ExperimentalCoroutinesApi
    @Singleton
    @Provides
    fun provideMoviesComicRepository(
        comicApiService: ComicApiService,
        @Named("provideMoviesCoroutinesDispatchers")
        coroutinesDispatcherProvider: CoroutinesDispatcherProvider
    ): ComicRepository {
        return ComicRepositoryImpl(comicApiService, coroutinesDispatcherProvider)
    }
}