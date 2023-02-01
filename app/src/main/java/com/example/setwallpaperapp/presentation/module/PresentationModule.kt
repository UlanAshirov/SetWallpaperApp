package com.example.setwallpaperapp.presentation.module

import com.example.setwallpaperapp.presentation.ui.activity.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { MainViewModel(get(), get()) }
}