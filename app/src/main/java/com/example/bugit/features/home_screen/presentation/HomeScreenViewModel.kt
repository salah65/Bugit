package com.example.bugit.features.home_screen.presentation

import com.example.bugit.core.BaseViewModel
import kotlinx.coroutines.flow.MutableStateFlow

class HomeScreenViewModel() : BaseViewModel<HomeScreenState, HomeScreenEvent>() {
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
        privateState.apply {
            value = value.copy(showLoadingDialog = true)
        }
    }

    private fun uploadBug() {

    }

    private fun clearFields() {
        privateState.value = privateState.value.copy(
            selectedImageUri = null,
            description = ""
        )
    }
}
