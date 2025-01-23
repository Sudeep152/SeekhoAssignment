package com.shashank.domain.repository

import com.shashank.common.utils.NetworkResult
import com.shashank.domain.domainModel.response.anime_detail.AnimeDetails

interface AnimeDetailsRepository {
    suspend fun getAnimeDetails(id: Int): NetworkResult<AnimeDetails>
}