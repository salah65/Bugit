package com.example.bugit.features.home_screen.domain.googleSheet.di

import com.example.bugit.features.home_screen.domain.googleSheet.repository.GoogleSheetRepository
import com.example.bugit.features.home_screen.domain.googleSheet.useCase.UploadBugToGoogleSheetUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object GoogleSheetUseCasesModule {

    @Provides
    @Singleton
    fun provideUploadBugToGoogleSheetUseCase(
        googleSheetRepository: GoogleSheetRepository
    ): UploadBugToGoogleSheetUseCase =
        UploadBugToGoogleSheetUseCase(googleSheetRepository)
}