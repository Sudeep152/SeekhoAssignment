package com.shashank.seekhoassignment.di.module

import com.shashank.data.repository.AnimeDetailsRepositoryImpl
import com.shashank.domain.repository.AnimeDetailsRepository
import com.shashank.domain.usecase.AnimeDetailsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object AnimeDetailsModule {
    @Singleton
    @Provides
    fun provideAnimeDetailsRepository(animeDetailsRepository: AnimeDetailsRepositoryImpl): AnimeDetailsRepository {
        return animeDetailsRepository
    }

    @Singleton
    @Provides
    fun provideAnimeDetailsUseCase(animeDetailsRepository: AnimeDetailsRepository): AnimeDetailsUseCase {
        return AnimeDetailsUseCase(animeDetailsRepository)
    }

}