package com.akmalmf.themoviedb.abstraction.data

/**
 * Created by Akmal Muhamad Firdaus on 2023/07/18 20:08
 * akmalmf007@gmail.com
 */
enum class HttpResult {
    NOT_DEFINED,
    NO_CONNECTION,
    TIMEOUT,
    CLIENT_ERROR,
    BAD_RESPONSE,
    SERVER_ERROR
}