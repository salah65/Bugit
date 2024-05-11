package com.example.bugit.features.home_screen.domain.image.di

import com.example.bugit.features.home_screen.domain.image.repository.ImageRepository
import com.example.bugit.features.home_screen.domain.image.useCase.GetImageUrlUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ImageUseCasesModule {

    @Provides
    @Singleton
    fun provideGetImageUrlUseCase(
        imageRepository: ImageRepository
    ): GetImageUrlUseCase =
        GetImageUrlUseCase(imageRepository)
}