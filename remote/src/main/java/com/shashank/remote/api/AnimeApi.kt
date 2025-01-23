package com.shashank.remote.api

import com.shashank.remote.model.response.anime_detail.AnimeDetailsResponse
import com.shashank.remote.model.response.top_anime.TopAnimeResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface AnimeApi {

    @GET("top/anime")
    suspend fun getTopAnimeList(): TopAnimeResponse

    @GET("anime/{id}")
    suspend fun getAnimeDetails(@Path("id") id: Int): AnimeDetailsResponse
}