package com.akmalmf.themoviedb.abstraction.base

import android.content.Context
import android.net.ConnectivityManager
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.akmalmf.themoviedb.R
import com.akmalmf.themoviedb.abstraction.data.HttpResult
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.snackbar.Snackbar

/**
 * Created by Akmal Muhamad Firdaus on 2023/07/19 07:47
 * akmalmf007@gmail.com
 */
abstract class BaseBottomSheetFragment : BottomSheetDialogFragment() {
    abstract fun initView(savedInstanceState: Bundle?)

    abstract fun initObservable()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView(savedInstanceState)
        initObservable()
    }

    fun toast(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }

    fun snackBarError(message: String) {
        Snackbar.make(requireActivity().findViewById(android.R.id.content), message, Snackbar.LENGTH_SHORT)
            .setBackgroundTint(ContextCompat.getColor(requireContext(), R.color.red_400))
            .show()
    }


    fun showNoConnectionAlert(
        errorMessage: String = "No connection!",
    ) {
        snackBarError(errorMessage)
    }

    private fun showTimeoutAlert(
        errorMessage: String = "Koneksi timeout. Mohon coba beberapa saat lagi",
    ) {
        snackBarError(errorMessage)
    }

    fun showClientAlert(
        errorMessage: String = "Terjadi kesalahan pada aplikasi. Mohon coba beberapa saat lagi",
        message: String? = null,
    ) {
        if (message!= null){
            snackBarError(message)
        }else{
            snackBarError(errorMessage)
        }
    }

    fun showServerErrorAlert(
        errorMessage: String = "Terjadi kendala pada server. Mohon coba beberapa saat lagi",
    ) {
        snackBarError(errorMessage)
    }

    fun showUnknownErrorAlert(
        errorMessage: String = "Terjadi kesalahan yang tak terduga. " +
                "Mohon hubungi customer service kami untuk bantuan lebih lanjut",
    ) {
        snackBarError(errorMessage)
    }


    fun showErrorAlert(cause: HttpResult, message: String?=null) {
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