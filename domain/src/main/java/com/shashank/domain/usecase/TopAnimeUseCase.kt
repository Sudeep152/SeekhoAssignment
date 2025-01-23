package com.shashank.domain.usecase

import com.shashank.domain.repository.TopAnimeRepository
import javax.inject.Inject

class TopAnimeUseCase @Inject constructor(private val topAnimeRepository: TopAnimeRepository) {
    suspend operator fun invoke() =topAnimeRepository.getTopAnimeList()

}