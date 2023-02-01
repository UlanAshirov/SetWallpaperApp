package com.example.setwallpaperapp.domain.usecases

import com.example.setwallpaperapp.domain.repo.PixabayRepository

class GetImageUseCase(private val repo: PixabayRepository) {

    operator fun invoke(category: String) =
        repo.getImage(category)
}