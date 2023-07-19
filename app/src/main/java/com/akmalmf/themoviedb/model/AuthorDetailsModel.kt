package com.akmalmf.themoviedb.model

import com.google.gson.annotations.SerializedName

/**
 * Created by Akmal Muhamad Firdaus on 2023/07/19 06:53
 * akmalmf007@gmail.com
 */
data class AuthorDetailsModel(
    @SerializedName("name") var name: String? = null,
    @SerializedName("username") var username: String? = null,
    @SerializedName("avatar_path") var avatarPath: String,
    @SerializedName("rating") var rating: Int? = null
)