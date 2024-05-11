package com.example.bugit.features.home_screen.domain.image.di

import android.content.Context
import com.example.bugit.features.home_screen.data.remote.ImageApi
import com.example.bugit.features.home_screen.data.repository.ImageRepositoryImp
import com.example.bugit.features.home_screen.data.utils.NetworkUtilities
import com.example.bugit.features.home_screen.domain.image.repository.ImageRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ImageRepositoryModule {

    @Provides
    @Singleton
    fun provideImageRepository(
        networkUtilities: NetworkUtilities,
        imageApi: ImageApi,
        @ApplicationContext context: Context,
    ): ImageRepository = ImageRepositoryImp(networkUtilities, imageApi, context)
}