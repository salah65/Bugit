package com.example.bugit.features.home_screen.domain.googleSheet.useCase

import com.example.bugit.features.home_screen.data.model.BugUploadDto
import com.example.bugit.features.home_screen.data.model.Resource
import com.example.bugit.features.home_screen.domain.googleSheet.repository.GoogleSheetRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class UploadBugToGoogleSheetUseCase(
    private val googleSheetRepository: GoogleSheetRepository
) {

    operator fun invoke(
        description: String,
        imageUrl: String,
        date: String
    ): Flow<Resource<BugUploadDto>> = flow {
        googleSheetRepository.uploadBugToGoogleSheet(description, imageUrl, date)
            .collect { resource ->
                when (resource) {
                    is Resource.Loading -> {
                        emit(Resource.Loading())
                    }

                    is Resource.Success -> {
                        emit(Resource.Success(BugUploadDto(true, "Bug reported successfully")))
                    }

                    is Resource.Error -> {
                        emit(Resource.Error(resource.error))
                    }
                }
            }
    }
}