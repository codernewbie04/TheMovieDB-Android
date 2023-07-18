package com.akmalmf.themoviedb.ui.main

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.akmalmf.themoviedb.abstraction.base.BaseActivity
import com.akmalmf.themoviedb.abstraction.data.Status
import com.akmalmf.themoviedb.databinding.ActivityMainBinding
import com.akmalmf.themoviedb.utils.toGone
import com.akmalmf.themoviedb.utils.toInvisible
import com.akmalmf.themoviedb.utils.toVisible
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity(){
    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels()

    private val trendingAdapter by lazy { TrendingAdapter() }
    private val genresAdapter by lazy { GenresAdapter() }
    private val moviesAdapter by lazy { MoviesAdapter() }

    lateinit var layoutManager: LinearLayoutManager



    override fun initView() {
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.rvTrending.adapter = trendingAdapter
        binding.rvGenres.adapter = genresAdapter
        binding.rvMovies.adapter = moviesAdapter
        layoutManager = LinearLayoutManager(this)
        binding.rvMovies.layoutManager = layoutManager
        binding.rvMovies.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                val layoutManager = recyclerView.layoutManager as LinearLayoutManager
                val lastVisibleItemPosition = layoutManager.findLastVisibleItemPosition()
                val totalItemCount = layoutManager.itemCount

                if (lastVisibleItemPosition == totalItemCount - 1) {
                    viewModel.loadMoreMovies()
                }
            }
        })

        viewModel.getTrendingMovies()
        viewModel.getGenres()
        viewModel.getMovies(null, 1)


        genresAdapter.onItemClick = {
            moviesAdapter.setItem(mutableListOf())
            if(viewModel.genre == it.id){
                viewModel.getMovies(null, 1)
            } else {
                viewModel.getMovies(it.id, 1)
            }
        }


        //trendingAdapter.onItemClick
    }

    override fun initObservable() {
        viewModel.trendingMovies.observe(this){
            when(it.status){
                Status.LOADING -> {
                    binding.rvTrending.toInvisible()
                    binding.progressBarTrending.toVisible()
                }
                Status.SUCCESS -> {
                    it.data?.let { it1 -> trendingAdapter.setItem(it1.results) }
                    binding.rvTrending.toVisible()
                    binding.progressBarTrending.toGone()
                }
                Status.ERROR ->{
                    binding.rvTrending.toVisible()
                    binding.progressBarTrending.toGone()
                    if(it.errorData !=null){
                        snackBarError(it.errorData.statusMessage)
                    } else {
                        showErrorAlert(it.cause, it.message)
                    }
                }
            }
        }

        viewModel.genres.observe(this){
            when(it.status){
                Status.LOADING -> {
                    binding.rvGenres.toInvisible()
                    binding.progressBarGenres.toVisible()
                }
                Status.SUCCESS -> {
                    it.data?.let { it1 -> genresAdapter.setItem(it1.genres) }
                    binding.rvGenres.toVisible()
                    binding.progressBarGenres.toGone()
                }
                Status.ERROR ->{
                    binding.rvGenres.toVisible()
                    binding.progressBarGenres.toGone()
                    if(it.errorData !=null){
                        snackBarError(it.errorData.statusMessage)
                    } else {
                        showErrorAlert(it.cause, it.message)
                    }
                }
            }
        }

        viewModel.movies.observe(this){
            when(it.status){
                Status.LOADING -> {
                    binding.progressBarMovies.toVisible()
                }
                Status.SUCCESS -> {
                    it.data?.let { it1 -> moviesAdapter.addItem(it1.results) }
                    binding.progressBarMovies.toGone()
                }
                Status.ERROR ->{
                    binding.progressBarMovies.toGone()
                    if(it.errorData !=null){
                        snackBarError(it.errorData.statusMessage)
                    } else {
                        showErrorAlert(it.cause, it.message)
                    }
                }
            }
        }
    }

    companion object{
        fun start(context: Context) {
            context.startActivity(Intent(context, MainActivity::class.java))
        }
    }
}