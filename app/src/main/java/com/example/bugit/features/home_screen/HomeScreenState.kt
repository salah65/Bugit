package com.example.bugit.features.home_screen

import android.net.Uri

data class HomeScreenState(
    val description: String = "",
    val selectedImageUri: Uri? = null,
    val imageUrl: String = "",
    val showLoadingDialog: Boolean = false,
    val showSuccessDialog: Boolean = false,
    val showErrorDialog: Boolean = false,
    val dialogText: String = "",
)
