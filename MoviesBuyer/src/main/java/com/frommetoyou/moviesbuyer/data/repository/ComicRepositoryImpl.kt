package com.frommetoyou.moviesbuyer.data.repository

import com.frommetoyou.moviesbuyer.data.extensions.getResponseError
import com.frommetoyou.moviesbuyer.data.extensions.loading
import com.frommetoyou.moviesbuyer.data.extensions.parseResponse
import com.frommetoyou.moviesbuyer.data.model.comics.ComicsDto
import com.frommetoyou.moviesbuyer.data.services.api.ComicApiService
import com.frommetoyou.moviesbuyer.data.util.CoroutinesDispatcherProvider
import com.frommetoyou.moviesbuyer.data.util.Result
import com.frommetoyou.moviesbuyer.domain.repository.ComicRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@ExperimentalCoroutinesApi
class ComicRepositoryImpl @Inject constructor(
    private val comicApiService: ComicApiService,
    private val coroutinesDispatcherProvider: CoroutinesDispatcherProvider
) : ComicRepository {
    override suspend fun getComics(
        apikey: String,
        hash: String,
        ts: Int
    ): Flow<Result<ComicsDto>> = flow {
        emit(comicApiService.getComics(apikey, hash, ts))
    }
        .catch { error ->
            error
            emit(error.getResponseError()) }
        .map { result -> result.parseResponse() }
        .flowOn(coroutinesDispatcherProvider.io)
}