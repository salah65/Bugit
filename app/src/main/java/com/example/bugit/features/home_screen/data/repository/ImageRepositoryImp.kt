package com.example.bugit.features.home_screen.data.repository

import android.content.Context
import android.net.Uri
import android.util.Log
import com.example.bugit.features.home_screen.data.model.ImageUrlDto
import com.example.bugit.features.home_screen.data.model.Resource
import com.example.bugit.features.home_screen.data.remote.ImageApi
import com.example.bugit.features.home_screen.data.utils.NetworkUtilities
import com.example.bugit.features.home_screen.data.utils.UploadStreamRequestBody
import com.example.bugit.features.home_screen.domain.image.repository.ImageRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okhttp3.MultipartBody
import retrofit2.Response
import javax.inject.Inject

class ImageRepositoryImp @Inject constructor(
    private val networkUtilities: NetworkUtilities,
    private val imageApi: ImageApi,
    private val context: Context
): ImageRepository {

    override fun getImageUrl(imageUri: Uri): Flow<Resource<Response<ImageUrlDto>>> = flow {
        networkUtilities.safeApiCall {
            val stream = context.contentResolver.openInputStream(imageUri)!!
            val request = UploadStreamRequestBody(
                mediaType = "image/*",
                inputStream = stream,
                onUploadProgress = {
                    Log.d("TEST", "On upload progress: $it")
                }
            )
            val imageFormData = MultipartBody.Part.createFormData(
                "image",
                imageUri.lastPathSegment,
                request
            )

            imageApi.getImageUrl(imageFormData = imageFormData)
        }.collect { results -> emit(results) }
    }
}