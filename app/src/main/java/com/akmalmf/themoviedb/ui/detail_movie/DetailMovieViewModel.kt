package com.akmalmf.themoviedb.ui.detail_movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.akmalmf.themoviedb.abstraction.data.Resource
import com.akmalmf.themoviedb.model.MovieDetailModel
import com.akmalmf.themoviedb.model.MoviesModel
import com.akmalmf.themoviedb.model.RatingsModel
import com.akmalmf.themoviedb.repository.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by Akmal Muhamad Firdaus on 2023/07/19 00:59
 * akmalmf007@gmail.com
 */
@HiltViewModel
class DetailMovieViewModel @Inject constructor(
    private val repository: MainRepository
) : ViewModel() {
    var currentPage = 1
    private val _movie = MutableLiveData<Resource<MovieDetailModel>>()
    val movie: LiveData<Resource<MovieDetailModel>>
        get() = _movie

    private val _ratings = MutableLiveData<Resource<RatingsModel>>()
    val ratings: LiveData<Resource<RatingsModel>>
        get() = _ratings

    fun detailMovie(movieId: Int){
        viewModelScope.launch(Dispatchers.IO){
            repository.getDetailMovie(movieId).collect{
                _movie.postValue(it)
            }
        }
    }

    fun reviews(movieId: Int, page:Int){
        viewModelScope.launch(Dispatchers.IO){
            repository.getReviews(movieId, page).collect{
                _ratings.postValue(it)
            }
        }
    }

    fun loadMoreReviews(movieId: Int){
        currentPage++
        reviews(movieId, currentPage)
    }
}