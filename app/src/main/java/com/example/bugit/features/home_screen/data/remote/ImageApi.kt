package com.example.bugit.features.home_screen.data.remote

import com.example.bugit.features.home_screen.data.model.ImageUrlDto
import okhttp3.MultipartBody
import retrofit2.Response
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Query

interface ImageApi {

    @Multipart
    @POST("upload")
    suspend fun getImageUrl(
        @Query("key") apiKey: String = "4a199a83cf2cc4ce715384fbd3ffac6a",
        @Part imageFormData: MultipartBody.Part
    ): Response<ImageUrlDto>
}