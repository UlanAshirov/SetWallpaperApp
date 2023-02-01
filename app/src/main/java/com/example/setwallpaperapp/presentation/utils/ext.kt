package com.example.setwallpaperapp.presentation.utils

import android.content.Context
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.core.view.isVisible
import com.bumptech.glide.Glide

fun Context.showToast(msg: String) {
    Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
}

fun ImageView.loadImage(url: String) {
    Glide.with(this).load(url).centerCrop().into(this)
}

fun View.gone() {
    this.isVisible = false
}

fun View.visible() {
    this.isVisible = true
}