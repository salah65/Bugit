package com.example.bugit.features.home_screen.domain.googleSheet.di

import com.example.bugit.features.home_screen.data.remote.GoogleSheetApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
object GoogleSheetApiModule {

    @Provides
    fun provideGoogleSheetApiModule(retrofit: Retrofit): GoogleSheetApi = retrofit.create(GoogleSheetApi::class.java)
}