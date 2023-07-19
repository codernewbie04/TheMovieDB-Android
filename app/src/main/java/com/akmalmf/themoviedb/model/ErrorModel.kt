package com.akmalmf.themoviedb.model

import com.google.gson.annotations.SerializedName

/**
 * Created by Akmal Muhamad Firdaus on 2023/07/18 21:06
 * akmalmf007@gmail.com
 */
data class ErrorModel(
    @SerializedName("status_code") var statusCode: Int,
    @SerializedName("status_message") var statusMessage: String,
    @SerializedName("success") var success: Boolean
)