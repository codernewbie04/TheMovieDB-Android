package com.akmalmf.themoviedb.model

import com.google.gson.annotations.SerializedName

/**
 * Created by Akmal Muhamad Firdaus on 2023/07/19 06:02
 * akmalmf007@gmail.com
 */
data class MovieDetailModel(
    @SerializedName("adult") var adult: Boolean,
    @SerializedName("backdrop_path") var backdropPath: String,
    @SerializedName("genres") var genres: ArrayList<GenreModel> = arrayListOf(),
    @SerializedName("id") var id: Int,
    @SerializedName("overview") var overview: String,
    @SerializedName("poster_path") var posterPath: String,
    @SerializedName("release_date") var releaseDate: String? = null,
    @SerializedName("tagline") var tagline: String? = null,
    @SerializedName("title") var title: String? = null,
    @SerializedName("vote_average") var voteAverage: Double,
    @SerializedName("vote_count") var voteCount: Int,
    @SerializedName("videos") var videos: VideoResultModel,
)