package com.example.bugit.features.home_screen.data.utils

import android.content.Context
import android.net.ConnectivityManager
import com.example.bugit.features.home_screen.data.model.ApiError
import com.example.bugit.features.home_screen.data.model.Resource
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import javax.inject.Inject

class NetworkUtilities @Inject constructor(
    @ApplicationContext private val context: Context,
) {

    private fun isOnline(): Boolean {
        val connectivityManager: ConnectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        return connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork) != null
    }

    fun <T : Any> safeApiCall(
        apiToBeCalled: suspend () -> T,
    ): Flow<Resource<T>> = flow {
        try {
            if (!isOnline())
                return@flow emit(
                    Resource.Error(
                    ApiError(
                        NetworkError.NO_INTERNET.code,
                        "Please connect to internet"
                    )
                ))

            emit(Resource.Loading())

            val response = apiToBeCalled()
            emit(Resource.Success(response))

        } catch (e: HttpException) {
            // You can handle every http exception here

            emit(
                Resource.Error(
                    ApiError(
                        e.code(),
                        e.message()
                    )
                )
            )
        }

        catch (e: Exception) {
            emit(
                Resource.Error(
                    ApiError(
                        NetworkError.UNKNOWN_ERROR.code,
                        "Unknown Error"
                    )
                )
            )
        }
    }

    enum class NetworkError(val code: Int) {
        // Add all http exception you need here and other exceptions
        NO_INTERNET(1000),
        UNKNOWN_ERROR(3000),
    }
}