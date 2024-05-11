package com.example.bugit.features.home_screen.data.repository

import com.example.bugit.features.home_screen.data.model.Resource
import com.example.bugit.features.home_screen.data.remote.GoogleSheetApi
import com.example.bugit.features.home_screen.data.utils.NetworkUtilities
import com.example.bugit.features.home_screen.domain.googleSheet.repository.GoogleSheetRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Response
import javax.inject.Inject

class GoogleSheetRepositoryImp @Inject constructor(
    private val networkUtilities: NetworkUtilities,
    private val googleSheetApi: GoogleSheetApi
) : GoogleSheetRepository {

    override fun uploadBugToGoogleSheet(
        description: String,
        imageUrl: String,
        date: String
    ): Flow<Resource<Response<Unit>>> = flow {
        networkUtilities.safeApiCall {
            googleSheetApi.uploadBugToGoogleSheet(
                description = description,
                imageUrl = imageUrl,
                sheetName = date
            )
        }.collect { results -> emit(results) }
    }
}