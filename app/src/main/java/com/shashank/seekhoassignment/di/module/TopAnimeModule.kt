package com.shashank.seekhoassignment.di.module

import com.shashank.data.repository.TopAnimeRepositoryImpl
import com.shashank.domain.repository.TopAnimeRepository
import com.shashank.domain.usecase.TopAnimeUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object TopAnimeModule {


    @Singleton
    @Provides
    fun provideTopAnimeRepository(topAnimeRepository: TopAnimeRepositoryImpl): TopAnimeRepository {
        return topAnimeRepository
    }


    @Singleton
    @Provides
    fun provideTopAnimeUseCase(topAnimeRepository: TopAnimeRepository): TopAnimeUseCase {
        return TopAnimeUseCase(topAnimeRepository)
    }

}