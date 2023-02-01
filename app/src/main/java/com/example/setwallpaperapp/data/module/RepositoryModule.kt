package com.example.setwallpaperapp.data.module

import com.example.setwallpaperapp.data.repo.PixabayRepositoryImpl
import com.example.setwallpaperapp.data.service.ApiService
import com.example.setwallpaperapp.domain.repo.PixabayRepository
import org.koin.dsl.module

val repoModule = module {
    single<PixabayRepository> { PixabayRepositoryImpl(get()) }
}