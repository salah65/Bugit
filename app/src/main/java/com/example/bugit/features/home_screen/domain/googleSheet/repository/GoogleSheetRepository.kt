package com.example.bugit.features.home_screen.domain.googleSheet.repository


import com.example.bugit.features.home_screen.data.model.Resource
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

interface GoogleSheetRepository {
    fun uploadBugToGoogleSheet(
        description: String, imageUrl: String, date: String
    ): Flow<Resource<Response<Unit>>>
}