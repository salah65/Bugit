package com.example.bugit.features.home_screen.domain.googleSheet.di

import com.example.bugit.features.home_screen.data.remote.GoogleSheetApi
import com.example.bugit.features.home_screen.data.repository.GoogleSheetRepositoryImp
import com.example.bugit.features.home_screen.data.utils.NetworkUtilities
import com.example.bugit.features.home_screen.domain.googleSheet.repository.GoogleSheetRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object GoogleSheetRepositoryModule {

    @Provides
    @Singleton
    fun provideGoogleSheetRepository(
        networkUtilities: NetworkUtilities,
        googleSheetApi: GoogleSheetApi
    ): GoogleSheetRepository = GoogleSheetRepositoryImp(networkUtilities, googleSheetApi)
}