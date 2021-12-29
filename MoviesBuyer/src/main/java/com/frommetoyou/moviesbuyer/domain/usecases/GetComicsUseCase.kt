package com.frommetoyou.moviesbuyer.domain.usecases

import com.frommetoyou.moviesbuyer.data.extensions.loading
import com.frommetoyou.moviesbuyer.data.model.comics.Comic
import com.frommetoyou.moviesbuyer.data.model.comics.ComicsDto
import com.frommetoyou.moviesbuyer.data.util.Result
import com.frommetoyou.moviesbuyer.domain.repository.ComicRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

//https://gateway.marvel.com/v1/public/comics?apikey=3a783b25c80e1c44875356dd363f272

private const val APIKEY = "3a783b25c80e1c44875356dd363f272d"
private const val HASH = "51a3ecf2f92a23817992a2663183325e"
private const val TS = 1

@ExperimentalCoroutinesApi
class GetComicsUseCase @Inject constructor(
    private val comicRepository: ComicRepository
) {
    suspend operator fun invoke(): Flow<Result<List<Comic>>> = flow {
        comicRepository.getComics(APIKEY, HASH, TS).collect { result ->
            when (result) {
                is Result.Success -> {
                    emit(Result.Success(result.data.data.results))
                }
                is Result.Error -> {
                    emit(result)
                }
            }
        }
    }.loading()
}