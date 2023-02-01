package com.example.setwallpaperapp.presentation.ui.activity

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.example.setwallpaperapp.databinding.ActivitySplashScreenBinding
import java.util.*


@SuppressLint("CustomSplashScreen")
class SplashScreenActivity : AppCompatActivity() {

    private var progres = 0

    private lateinit var binding: ActivitySplashScreenBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)
        start()
    }

    private fun start() {
        Handler(Looper.getMainLooper()).postDelayed({
            if (progres >= 1500) {
                startApp()
                finish()
            } else {
                start()
                progres += 100
                binding.launchProgress.progress = progres
            }
        }, 100)
    }

    private fun startApp() {
        val intent = Intent(this@SplashScreenActivity, MainActivity::class.java)
        startActivity(intent)
    }
}