package com.akmalmf.themoviedb.di

import com.akmalmf.themoviedb.BuildConfig
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

/**
 * Created by Akmal Muhamad Firdaus on 2023/07/18 22:11
 * akmalmf007@gmail.com
 */

class ApikeyInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        var request: Request = chain.request()
        val finalToken = "Bearer ${BuildConfig.API_KEY}"
        request.newBuilder()
            .addHeader("Authorization", finalToken)
            .build()
        return chain.proceed(request)
    }
}