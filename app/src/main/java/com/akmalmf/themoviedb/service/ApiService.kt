package com.akmalmf.themoviedb.service

import com.akmalmf.themoviedb.BuildConfig
import com.akmalmf.themoviedb.model.GenreModel
import com.akmalmf.themoviedb.model.GenresModel
import com.akmalmf.themoviedb.model.MoviesModel
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.Query

/**
 * Created by Akmal Muhamad Firdaus on 2023/07/18 20:14
 * akmalmf007@gmail.com
 */
interface ApiService {
    companion object {
        private const val KEY = BuildConfig.API_KEY
    }

    @GET("trending/movie/week")
    @Headers("Authorization: Bearer $KEY")
    suspend fun trendingMovies(): MoviesModel


    @GET("genre/movie/list")
    @Headers("Authorization: Bearer $KEY")
    suspend fun genres(): GenresModel

    @GET("discover/movie")
    @Headers("Authorization: Bearer $KEY")
    suspend fun movies(
        @Query("with_genres") genreId: Int? = null,
        @Query("page") page: Int
    ): MoviesModel
}