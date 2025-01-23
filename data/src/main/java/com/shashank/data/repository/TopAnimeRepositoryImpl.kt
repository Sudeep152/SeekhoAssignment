package com.shashank.data.repository

import com.shashank.common.utils.NetworkResult
import com.shashank.data.mapper.toDomain
import com.shashank.domain.domainModel.response.top_anime.TopAnime
import com.shashank.domain.repository.TopAnimeRepository
import com.shashank.remote.api.AnimeApi
import javax.inject.Inject

class TopAnimeRepositoryImpl @Inject constructor(private val animeApi: AnimeApi) :
    TopAnimeRepository {
    override suspend fun getTopAnimeList(): NetworkResult<TopAnime> {
        return try {
            val response = animeApi.getTopAnimeList()
            NetworkResult.Success(response.toDomain())
        } catch (e: Exception) {
            NetworkResult.Error("Error occurred: ${e.message}")
        }
    }
}