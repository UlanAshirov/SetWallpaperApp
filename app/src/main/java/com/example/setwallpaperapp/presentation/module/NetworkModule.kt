package com.example.setwallpaperapp.presentation.module

import android.content.Context
import android.net.ConnectivityManager
import com.example.setwallpaperapp.presentation.utils.networkstate.ListenNetwork
import org.koin.dsl.module

val networkStateModule = module {
    factory { ListenNetwork(provideConnectivityManager(get())) }
}

fun provideConnectivityManager(context: Context): ConnectivityManager {
    return context.applicationContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
}