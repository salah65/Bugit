package com.example.bugit.features.home_screen.presentation

import androidx.lifecycle.viewModelScope
import com.example.bugit.features.home_screen.data.model.Resource
import com.example.bugit.features.home_screen.domain.googleSheet.useCase.UploadBugToGoogleSheetUseCase
import com.example.bugit.features.home_screen.domain.image.useCase.GetImageUrlUseCase
import com.example.bugit.features.home_screen.presentation.core.BaseViewModel
import com.example.bugit.features.home_screen.presentation.core.getCurrentDateFormatted
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import java.util.Date
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor(
    private val getImageUrlUseCase: GetImageUrlUseCase,
    private val uploadBugUseCase: UploadBugToGoogleSheetUseCase
) : BaseViewModel<HomeScreenState, HomeScreenEvent>() {
    override val privateState = MutableStateFlow(HomeScreenState())

    override fun onEvent(event: HomeScreenEvent) {
        privateState.apply {
            when (event) {
                is HomeScreenEvent.OnDescriptionChanged -> value =
                    privateState.value.copy(description = event.newText)

                is HomeScreenEvent.OnImageUriChanged -> value =
                    privateState.value.copy(selectedImageUri = event.newUri)

                is HomeScreenEvent.Submit -> submit()
                is HomeScreenEvent.DismissAllDialog -> value = privateState.value.copy(
                    showErrorDialog = false,
                    showSuccessDialog = false,
                    showLoadingDialog = false
                )
            }
        }
    }

    private fun submit() {
        if (privateState.value.description.isEmpty()
            || privateState.value.selectedImageUri == null
        ) {
            privateState.value = privateState.value.copy(
                showErrorDialog = true,
                dialogText = "Please fill all required fields"
            )
            return
        }

        getImageUrlUseCase(
            imageUri = privateState.value.selectedImageUri!!
        ).onEach { resource ->
            privateState.apply {
                when (resource) {
                    is Resource.Loading -> {
                        onEvent(HomeScreenEvent.DismissAllDialog)
                        value = value.copy(showLoadingDialog = true)
                    }

                    is Resource.Success -> {
                        value = value.copy(
                            imageUrl = resource.data.data.displayUrl,
                            showLoadingDialog = false
                        )
                        uploadBug()
                    }

                    is Resource.Error -> {
                        onEvent(HomeScreenEvent.DismissAllDialog)
                        value =
                            value.copy(showErrorDialog = true, dialogText = resource.error.message)
                    }
                }
            }

        }.launchIn(viewModelScope)
    }

    private fun uploadBug() {
        uploadBugUseCase(
            description = privateState.value.description,
            imageUrl = privateState.value.imageUrl,
            date = Date().getCurrentDateFormatted()
        ).onEach { resource ->
            privateState.apply {
                when (resource) {
                    is Resource.Loading -> Unit
                    is Resource.Success -> {
                        onEvent(HomeScreenEvent.DismissAllDialog)
                        value = value.copy(showSuccessDialog = true, dialogText = resource.data.message)
                        clearFields()
                    }

                    is Resource.Error -> {
                        onEvent(HomeScreenEvent.DismissAllDialog)
                        value =
                            value.copy(showErrorDialog = true, dialogText = resource.error.message)
                    }
                }
            }
        }.launchIn(viewModelScope)
    }

    private fun clearFields() {
        privateState.value = privateState.value.copy(
            selectedImageUri = null,
            description = ""
        )
    }
}
