package com.rtr.omdbfilms.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build

/**
 * Created by RAHUL T R
 * Copyright (c) 2020 . All rights reserved.
 */


/**
 * Object utility class for network related features
 */
object NetworkUtils {

    /**
     * Method to check internet availability
     */
    private fun isNetworkAvailable(connectivityManager: ConnectivityManager?): Boolean {
        connectivityManager?.let {
            val networkInfo = it.activeNetworkInfo
            networkInfo?.let {  networkInfoLocal ->
                return networkInfoLocal.isConnected
            }
        }
        return false
    }

    /**
     * Method to check internet connectivity
     */
    fun isInternetAvailable(context: Context): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val networkCapabilities = connectivityManager.activeNetwork ?: return false
            val actNw =
                connectivityManager.getNetworkCapabilities(networkCapabilities) ?: return false
            return  when {
                actNw.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                actNw.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                actNw.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
                else -> false
            }
        } else return isNetworkAvailable(connectivityManager)
    }
}