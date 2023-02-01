package com.example.setwallpaperapp.data.mapper

interface DataMapper<T> {
    fun toDomain(): T
}