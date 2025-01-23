package com.shashank.domain.usecase

import com.shashank.domain.repository.AnimeDetailsRepository
import javax.inject.Inject

class AnimeDetailsUseCase @Inject constructor(private val animeDetailsRepository: AnimeDetailsRepository) {
    suspend operator fun invoke(id: Int) = animeDetailsRepository.getAnimeDetails(id)
}