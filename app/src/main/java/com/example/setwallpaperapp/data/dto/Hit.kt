package com.example.setwallpaperapp.data.dto

import com.example.setwallpaperapp.data.mapper.DataMapper
import com.example.setwallpaperapp.domain.model.HitModel

data class Hit(
    val id: Int,
    val largeImageURL: String,
    val pageURL: String,
    val previewURL: String,
) : DataMapper<HitModel> {

    override fun toDomain() = HitModel(
        id = id,
        largeImageURL = largeImageURL,
        pageURL = pageURL,
        previewURL = previewURL,
    )
}