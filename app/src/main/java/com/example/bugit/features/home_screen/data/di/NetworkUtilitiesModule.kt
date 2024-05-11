package com.example.bugit.features.home_screen.data.di

import android.content.Context
import com.example.bugit.features.home_screen.data.utils.NetworkUtilities
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkUtilitiesModule {

    @Provides
    @Singleton
    fun provideNetworkUtilities(
        @ApplicationContext context: Context
    ): NetworkUtilities =
        NetworkUtilities(context)
}