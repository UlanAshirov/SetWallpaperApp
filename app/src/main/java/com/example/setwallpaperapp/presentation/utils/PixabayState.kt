package com.example.setwallpaperapp.presentation.utils

sealed class PixabayState<T> {
    class Success<T>(val data : T) : PixabayState<T>()
    class Loading<T>() : PixabayState<T>()
    class Error<T>(val message: String) : PixabayState<T>()
}