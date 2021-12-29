package com.frommetoyou.moviesbuyer.data.services.api

import com.frommetoyou.moviesbuyer.data.model.comics.ComicsDto
import retrofit2.Response
import retrofit2.http.*

interface ComicApiService {
    @GET("public/comics")
    suspend fun getComics(
        @Query("apikey") apiKey: String,
        @Query("hash") hash: String,
        @Query("ts") ts: Int
    ): Response<ComicsDto>
}
