package com.akmalmf.themoviedb.model

import com.google.gson.annotations.SerializedName

/**
 * Created by Akmal Muhamad Firdaus on 2023/07/18 20:51
 * akmalmf007@gmail.com
 */
data class MoviesModel(
    @SerializedName("page") var page: Int? = null,
    @SerializedName("results") var results: ArrayList<MovieModel> = arrayListOf(),
    @SerializedName("total_pages") var totalPages: Int? = null,
    @SerializedName("total_results") var totalResults: Int? = null
)