package com.akmalmf.themoviedb.ui.detail_movie

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.akmalmf.themoviedb.R
import com.akmalmf.themoviedb.abstraction.base.BaseActivity
import com.akmalmf.themoviedb.databinding.ActivityDetailMovieBinding
import com.akmalmf.themoviedb.ui.main.MainActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailMovieActivity : BaseActivity() {
    private lateinit var binding: ActivityDetailMovieBinding


    override fun initView() {
        binding = ActivityDetailMovieBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun initObservable() {
        TODO("Not yet implemented")
    }

    companion object{
        fun start(context: Context) {
            context.startActivity(Intent(context, MainActivity::class.java))
        }
    }
}