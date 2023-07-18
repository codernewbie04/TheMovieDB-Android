package com.akmalmf.themoviedb.repository

import androidx.lifecycle.liveData
import com.akmalmf.themoviedb.abstraction.data.RemoteDataSource
import com.akmalmf.themoviedb.abstraction.data.Resource
import com.akmalmf.themoviedb.model.GenresModel
import com.akmalmf.themoviedb.model.MoviesModel
import com.akmalmf.themoviedb.service.ApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

/**
 * Created by Akmal Muhamad Firdaus on 2023/07/18 20:23
 * akmalmf007@gmail.com
 */
class MainRepository @Inject constructor(
    private val api: ApiService
) : RemoteDataSource(){
    suspend fun getTrendingMovies(): Flow<Resource<MoviesModel>> = flow {
        emit(Resource.loading())
        emit(safeApiCall {
            api.trendingMovies()
        })
    }.flowOn(Dispatchers.IO)

    suspend fun getGenres(): Flow<Resource<GenresModel>> = flow{
        emit(Resource.loading())
        emit(safeApiCall {
            api.genres()
        })
    }.flowOn(Dispatchers.IO)


    suspend fun getMovies(genreId: Int?, page: Int): Flow<Resource<MoviesModel>> = flow {
        emit(Resource.loading())
        emit(safeApiCall {
            api.movies(genreId, page)
        })
    }.flowOn(Dispatchers.IO)
}