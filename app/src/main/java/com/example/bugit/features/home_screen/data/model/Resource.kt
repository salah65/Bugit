package com.example.bugit.features.home_screen.data.model

sealed class Resource<T : Any>{
    class Loading<T : Any> : Resource<T>()
    data class Success<T : Any>(val data: T) : Resource<T>()
    data class Error<T : Any>(val error: ApiError) : Resource<T>()
}
