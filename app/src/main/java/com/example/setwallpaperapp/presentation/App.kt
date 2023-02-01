package com.example.setwallpaperapp.presentation

import android.app.Application
import com.example.setwallpaperapp.data.module.networkModule
import com.example.setwallpaperapp.data.module.repoModule
import com.example.setwallpaperapp.domain.module.domainModule
import com.example.setwallpaperapp.presentation.module.networkStateModule
import com.example.setwallpaperapp.presentation.module.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            modules(networkModule, repoModule, domainModule, viewModelModule, networkStateModule)
        }
    }
}