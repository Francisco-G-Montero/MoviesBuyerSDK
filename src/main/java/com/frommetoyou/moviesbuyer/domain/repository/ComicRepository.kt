package com.frommetoyou.moviesbuyer.domain.repository

import com.frommetoyou.moviesbuyer.data.model.comics.ComicsDto
import com.frommetoyou.moviesbuyer.data.util.Result
import kotlinx.coroutines.flow.Flow

interface ComicRepository {
    suspend fun getComics(apikey: String, hash: String, ts: Int): Flow<Result<ComicsDto>>
}