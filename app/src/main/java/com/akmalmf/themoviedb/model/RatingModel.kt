package com.akmalmf.themoviedb.model

import com.google.gson.annotations.SerializedName

/**
 * Created by Akmal Muhamad Firdaus on 2023/07/19 06:53
 * akmalmf007@gmail.com
 */
data class RatingModel(
    @SerializedName("author") var author: String? = null,
    @SerializedName("author_details") var authorDetails: AuthorDetailsModel,
    @SerializedName("content") var content: String? = null,
    @SerializedName("created_at") var createdAt: String? = null,
    @SerializedName("id") var id: String? = null,
    @SerializedName("updated_at") var updatedAt: String? = null,
    @SerializedName("url") var url: String? = null
)