package com.akmalmf.themoviedb.abstraction.base

import android.content.Context
import android.net.ConnectivityManager
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.akmalmf.themoviedb.R
import com.akmalmf.themoviedb.abstraction.data.HttpResult
import com.google.android.material.snackbar.Snackbar

/**
 * Created by Akmal Muhamad Firdaus on 2023/07/18 20:06
 * akmalmf007@gmail.com
 */
abstract class BaseActivity : AppCompatActivity() {
    abstract fun initView()

    abstract fun initObservable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initView()
        initObservable()
    }

    fun toast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    fun snackBarSuccess(message: String) {
        Snackbar.make(findViewById(android.R.id.content), message, Snackbar.LENGTH_SHORT)
            .setBackgroundTint(ContextCompat.getColor(this, R.color.green_500))
            .show()
    }

    fun snackBarError(message: String) {
        Snackbar.make(findViewById(android.R.id.content), message, Snackbar.LENGTH_SHORT)
            .setBackgroundTint(ContextCompat.getColor(this, R.color.red_400))
            .show()
    }

    fun snackBarErrorLong(message: String) {
        Snackbar.make(findViewById(android.R.id.content), message, Snackbar.LENGTH_LONG)
            .setBackgroundTint(ContextCompat.getColor(this, R.color.red_400))
            .show()
    }

    fun snackBar(message: String) {
        Snackbar.make(findViewById(android.R.id.content), message, Snackbar.LENGTH_SHORT).show()
    }


    private fun showNoConnectionAlert(
        errorMessage: String = "No Connection!",
    ) {
        snackBarError(errorMessage)
    }

    private fun showTimeoutAlert(
        errorMessage: String = "Koneksi timeout. Mohon coba beberapa saat lagi",
    ) {
        snackBarError(errorMessage)
    }

    private fun showClientAlert(
        errorMessage: String = "Terjadi kesalahan pada aplikasi. Mohon coba beberapa saat lagi",
        message: String? = null
    ) {
        if (message != null) {
            snackBarError(message)
        } else {
            snackBarError(errorMessage)
        }
    }

    private fun showServerErrorAlert(
        errorMessage: String = "Terjadi kendala pada server. Mohon coba beberapa saat lagi",
    ) {
        snackBarError(errorMessage)
    }

    private fun showUnknownErrorAlert(
        errorMessage: String = "Terjadi kesalahan yang tak terduga. " +
                "Mohon hubungi customer service kami untuk bantuan lebih lanjut",
    ) {
        snackBarError(errorMessage)
    }

    fun showErrorAlert(cause: HttpResult, message: String? = null) {
        when (cause) {
            HttpResult.NO_CONNECTION -> showNoConnectionAlert()
            HttpResult.TIMEOUT -> showTimeoutAlert()
            HttpResult.CLIENT_ERROR -> showClientAlert(message = message)
            HttpResult.BAD_RESPONSE -> showUnknownErrorAlert()
            HttpResult.SERVER_ERROR -> showServerErrorAlert()
            HttpResult.NOT_DEFINED -> showUnknownErrorAlert()
        }
    }
}