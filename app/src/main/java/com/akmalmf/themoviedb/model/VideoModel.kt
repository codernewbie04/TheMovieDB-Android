package com.akmalmf.themoviedb.model

import com.google.gson.annotations.SerializedName

/**
 * Created by Akmal Muhamad Firdaus on 2023/07/19 06:08
 * akmalmf007@gmail.com
 */
data class VideoModel(
    @SerializedName("key") var key: String,
    @SerializedName("site") var site: String,
    @SerializedName("id") var id: String
)
