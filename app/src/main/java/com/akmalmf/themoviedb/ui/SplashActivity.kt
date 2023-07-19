package com.akmalmf.themoviedb.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.akmalmf.themoviedb.R
import com.akmalmf.themoviedb.ui.main.MainActivity

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        Handler().postDelayed({
            MainActivity.start(this)
            finishAffinity()
        }, 2100)
    }
}