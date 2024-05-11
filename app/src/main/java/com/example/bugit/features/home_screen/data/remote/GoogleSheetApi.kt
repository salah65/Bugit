package com.example.bugit.features.home_screen.data.remote

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface GoogleSheetApi {

    @GET("exec")
    suspend fun uploadBugToGoogleSheet(
        @Query("path") sheetName: String,
        @Query("action") action: String = "write",
        @Query("description") description: String,
        @Query("imageUrl") imageUrl: String
    ): Response<Unit>
}