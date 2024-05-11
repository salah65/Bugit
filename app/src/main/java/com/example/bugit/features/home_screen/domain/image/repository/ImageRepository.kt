package com.example.bugit.features.home_screen.domain.image.repository

import android.net.Uri
import com.example.bugit.features.home_screen.data.model.ImageUrlDto
import com.example.bugit.features.home_screen.data.model.Resource
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

interface ImageRepository {
    fun getImageUrl(imageUri: Uri): Flow<Resource<Response<ImageUrlDto>>>
}