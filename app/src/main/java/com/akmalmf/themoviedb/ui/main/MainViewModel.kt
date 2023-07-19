package com.akmalmf.themoviedb.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.akmalmf.themoviedb.abstraction.data.Resource
import com.akmalmf.themoviedb.model.GenresModel
import com.akmalmf.themoviedb.model.MoviesModel
import com.akmalmf.themoviedb.repository.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by Akmal Muhamad Firdaus on 2023/07/18 20:25
 * akmalmf007@gmail.com
 */
@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: MainRepository
): ViewModel(){
    var currentPage = 1

    var genre: Int? =null

    private val _trendingMovies = MutableLiveData<Resource<MoviesModel>>()
    val trendingMovies: LiveData<Resource<MoviesModel>>
        get() = _trendingMovies

    private val _genres = MutableLiveData<Resource<GenresModel>>()
    val genres: LiveData<Resource<GenresModel>>
        get() = _genres


    private val _movies = MutableLiveData<Resource<MoviesModel>>()
    val movies: LiveData<Resource<MoviesModel>>
        get() = _movies


    fun getTrendingMovies(){
        viewModelScope.launch(Dispatchers.IO) {
            repository.getTrendingMovies().collect{
                _trendingMovies.postValue(it)
            }
        }
    }

    fun getGenres(){
        viewModelScope.launch(Dispatchers.IO){
            repository.getGenres().collect{
                _genres.postValue(it)
            }
        }
    }

    fun getMovies(genreId: Int?, page: Int){
        genre = genreId
        viewModelScope.launch(Dispatchers.IO){
            repository.getMovies(genreId, page).collect{
                _movies.postValue(it)
            }
        }
    }

    fun loadMoreMovies(){
        currentPage++
        getMovies(genre, currentPage)
    }
}