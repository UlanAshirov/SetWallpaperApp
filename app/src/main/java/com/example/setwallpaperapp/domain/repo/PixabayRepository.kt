package com.example.setwallpaperapp.domain.repo

import com.example.setwallpaperapp.domain.model.HitModel
import com.example.setwallpaperapp.presentation.utils.PixabayState
import com.example.setwallpaperapp.resourse.Resource
import kotlinx.coroutines.flow.Flow

interface PixabayRepository {

     fun getImage(category: String): Flow<Resource<List<HitModel>>>
}