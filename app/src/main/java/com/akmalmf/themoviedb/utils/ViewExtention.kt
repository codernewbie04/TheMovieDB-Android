package com.akmalmf.themoviedb.utils

import android.view.View

/**
 * Created by Akmal Muhamad Firdaus on 2023/07/18 22:35
 * akmalmf007@gmail.com
 */
fun View.toVisible() {
    visibility = View.VISIBLE
}

fun View.toInvisible() {
    visibility = View.INVISIBLE
}

fun View.toGone() {
    visibility = View.GONE
}

fun View.isVisible() = visibility == View.VISIBLE

fun View.isGone() = visibility == View.GONE