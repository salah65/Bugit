package com.example.bugit.features.home_screen.domain.image.useCase

import android.net.Uri
import com.example.bugit.features.home_screen.data.model.ImageUrlDto
import com.example.bugit.features.home_screen.data.model.Resource
import com.example.bugit.features.home_screen.domain.image.repository.ImageRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetImageUrlUseCase @Inject constructor(
    private val imageRepository: ImageRepository
) {

    operator fun invoke(imageUri: Uri): Flow<Resource<ImageUrlDto>> = flow {
        imageRepository.getImageUrl(imageUri).collect { resource ->
            when (resource) {
                is Resource.Loading -> {
                    emit(Resource.Loading())
                }
                is Resource.Success -> {
                    emit(Resource.Success(resource.data.body()!!))
                }
                is Resource.Error -> {
                    emit(Resource.Error(resource.error))
                }
            }
        }
    }
}