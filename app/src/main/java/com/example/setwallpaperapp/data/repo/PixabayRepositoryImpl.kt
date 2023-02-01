package com.example.setwallpaperapp.data.repo

import com.example.setwallpaperapp.data.service.ApiService
import com.example.setwallpaperapp.domain.model.HitModel
import com.example.setwallpaperapp.domain.repo.PixabayRepository
import com.example.setwallpaperapp.presentation.utils.PixabayState
import com.example.setwallpaperapp.resourse.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException

class PixabayRepositoryImpl(private val api: ApiService) : PixabayRepository {

    override fun getImage(category: String): Flow<Resource<List<HitModel>>> = flow {
        try {
            emit(Resource.Loading())
            val result = api.getImage(category = category).hits.map { it.toDomain() }
            emit(Resource.Success(data = result))
        } catch (e: java.lang.Exception) {
            emit(Resource.Error(message = "$e"))
        } catch (e: IOException) {
            emit(Resource.Error(message = "$e"))
        }
    }
}