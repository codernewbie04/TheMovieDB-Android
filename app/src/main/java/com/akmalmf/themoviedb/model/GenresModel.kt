package com.akmalmf.themoviedb.model

import com.google.gson.annotations.SerializedName

/**
 * Created by Akmal Muhamad Firdaus on 2023/07/18 23:14
 * akmalmf007@gmail.com
 */
data class GenresModel(
    @SerializedName("genres")
    val genres: ArrayList<GenreModel>
)