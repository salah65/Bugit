package com.example.bugit.features.home_screen.domain.image.di

import com.example.bugit.features.home_screen.data.remote.ImageApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Named

@Module
@InstallIn(SingletonComponent::class)
object ImageApiModule {

    @Provides
    fun provideImageApi(
        @Named("ImageRetrofit") retrofit: Retrofit
    ): ImageApi = retrofit.create(ImageApi::class.java)
}