package com.example.setwallpaperapp.domain.module

import com.example.setwallpaperapp.domain.usecases.GetImageUseCase
import org.koin.dsl.module

val domainModule = module {
    factory { GetImageUseCase(get()) }
}