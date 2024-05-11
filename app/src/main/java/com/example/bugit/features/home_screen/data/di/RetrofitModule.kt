package com.example.bugit.features.home_screen.data.di

import com.example.bugit.features.home_screen.data.utils.NetworkConstant
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RetrofitModule {
    @Singleton
    @Provides
    @Named("ImageRetrofit")
    fun provideImageRetrofit(): Retrofit =
        Retrofit.Builder()
            .baseUrl(NetworkConstant.IMAGE_API_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
}