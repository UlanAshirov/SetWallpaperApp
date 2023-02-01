package com.example.setwallpaperapp.data.service

import com.example.setwallpaperapp.BuildConfig.API_KEY
import com.example.setwallpaperapp.data.dto.Hit
import com.example.setwallpaperapp.data.dto.PixabayResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("api")
    suspend fun getImage(
        @Query("key") apiKey: String = API_KEY,
        @Query("category") category: String? = null
    ): PixabayResponse<Hit>
}