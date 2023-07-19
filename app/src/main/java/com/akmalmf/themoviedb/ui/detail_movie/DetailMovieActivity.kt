package com.akmalmf.themoviedb.ui.detail_movie

import android.content.Context
import android.content.Intent
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.akmalmf.themoviedb.BuildConfig
import com.akmalmf.themoviedb.R
import com.akmalmf.themoviedb.abstraction.base.BaseActivity
import com.akmalmf.themoviedb.abstraction.data.Status
import com.akmalmf.themoviedb.databinding.ActivityDetailMovieBinding
import com.akmalmf.themoviedb.utils.toGone
import com.akmalmf.themoviedb.utils.toVisible
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailMovieActivity : BaseActivity() {
    private lateinit var binding: ActivityDetailMovieBinding
    private val viewModel: DetailMovieViewModel by viewModels()

    private val ratingAdapter by lazy { RatingAdapter() }


    var loadMore = true
    override fun initView() {
        binding = ActivityDetailMovieBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val movieId = intent.getIntExtra(MOVIE_KEY, -1)
        if (movieId == -1) {
            snackBar("Whoops, we couldn't find the movie id")
            finish()
        }
        binding.rvRatings.adapter = ratingAdapter
        viewModel.detailMovie(movieId)
        viewModel.reviews(movieId, 1)
        binding.rvRatings.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                val layoutManager = recyclerView.layoutManager as LinearLayoutManager
                val lastVisibleItemPosition = layoutManager.findLastVisibleItemPosition()
                val totalItemCount = layoutManager.itemCount

                if (lastVisibleItemPosition == totalItemCount - 1 && loadMore) {
                    viewModel.loadMoreReviews(movieId)
                }
            }
        })


    }

    override fun initObservable() {
        viewModel.movie.observe(this) {
            when (it.status) {
                Status.LOADING -> {
                    binding.progressBar.toVisible()
                }

                Status.SUCCESS -> {
                    it.data?.let { movie ->
                        binding.textTitle.text = movie.title
                        binding.ratingBar.rating = movie.voteAverage.toFloat()
                        binding.textRating.text = "${movie.voteAverage}/10"
                        val genreNames = movie.genres.joinToString(", ") { genre -> genre.name }
                        binding.textGenres.text = genreNames
                        binding.textOverview.text = movie.overview
                        binding.imagePoster.load(BuildConfig.BASE_IMAGE + "original" + movie.posterPath) {
                            placeholder(R.drawable.ic_image_placeholder)
                            error(R.drawable.ic_image_error)
                        }
                        binding.imageBackdrop.load(BuildConfig.BASE_IMAGE + "original" + movie.backdropPath) {
                            placeholder(R.drawable.ic_image_placeholder)
                            error(R.drawable.ic_image_error)
                        }
                        if(movie.videos.results.isNotEmpty()){
                            binding.imagePlay.setOnClickListener {
                                VideoBottomSheet.newInstance(movie.videos.results[0].key).show(supportFragmentManager, "VideoPlayer")

                            }
                        }


                    }
                    binding.progressBar.toGone()
                }

                Status.ERROR -> {
                    binding.progressBar.toGone()
                    if (it.errorData != null) {
                        snackBarError(it.errorData.statusMessage)
                    } else {
                        showErrorAlert(it.cause, it.message)
                    }
                }
            }
        }

        viewModel.ratings.observe(this) {
            when (it.status) {
                Status.LOADING -> {
                    binding.progressBarReviews.toVisible()
                }

                Status.SUCCESS -> {

                    it.data?.let { it1 ->
                        loadMore = it1.page != it1.totalPages
                        ratingAdapter.addItem(it1.results)
                        if(it1.results.isEmpty()){
                            loadMore = false
                            binding.textNodata.toVisible()
                            binding.rvRatings.toGone()
                            binding.progressBarReviews.toGone()
                        }
                    }
                    binding.rvRatings.toVisible()
                    binding.progressBarReviews.toGone()

                }

                Status.ERROR -> {
                    binding.rvRatings.toVisible()
                    binding.progressBarReviews.toGone()
                    if (it.errorData != null) {
                        snackBarError(it.errorData.statusMessage)
                    } else {
                        showErrorAlert(it.cause, it.message)
                    }
                }
            }
        }
    }

    companion object {
        const val MOVIE_KEY = "movie_id"
        fun start(context: Context, movieId: Int) {
            val i = Intent(context, DetailMovieActivity::class.java)
            i.putExtra(MOVIE_KEY, movieId)
            context.startActivity(i)
        }
    }
}