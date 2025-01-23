package com.shashank.data.repository

import com.shashank.common.utils.NetworkResult
import com.shashank.data.mapper.toDomain
import com.shashank.domain.domainModel.response.anime_detail.AnimeDetails
import com.shashank.domain.repository.AnimeDetailsRepository
import com.shashank.remote.api.AnimeApi
import javax.inject.Inject

class AnimeDetailsRepositoryImpl @Inject constructor(private val animeApi: AnimeApi) :
    AnimeDetailsRepository {

    override suspend fun getAnimeDetails(id: Int): NetworkResult<AnimeDetails> {
        return try {
            val response = animeApi.getAnimeDetails(id)
            NetworkResult.Success(response.toDomain())
        } catch (e: Exception) {
            NetworkResult.Error("Error occurred: ${e.message}")
        }
    }
}