package com.example.bugit.home_screen

import android.net.Uri

sealed class HomeScreenEvent{
    data class OnDescriptionChanged(val newText: String): HomeScreenEvent()
    data class OnImageUriChanged(val newUri: Uri?): HomeScreenEvent()
    data object Submit: HomeScreenEvent()
    data object DismissAllDialog: HomeScreenEvent()
}
