package com.shashank.domain.repository

import com.shashank.common.utils.NetworkResult
import com.shashank.domain.domainModel.response.top_anime.TopAnime

interface TopAnimeRepository {
    suspend fun getTopAnimeList(): NetworkResult<TopAnime>
}