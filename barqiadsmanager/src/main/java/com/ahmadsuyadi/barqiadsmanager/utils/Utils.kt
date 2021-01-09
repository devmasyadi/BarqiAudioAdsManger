package com.ahmadsuyadi.barqiadsmanager.utils

import android.annotation.SuppressLint
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build

@SuppressLint("MissingPermission")
@Suppress("DEPRECATION")
fun Context.isOnline(): Boolean {
    var result = false
    val cm = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager?
    cm?.apply {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getNetworkCapabilities(activeNetwork)?.apply {
                result =
                    hasTransport(NetworkCapabilities.TRANSPORT_WIFI) || hasTransport(
                        NetworkCapabilities.TRANSPORT_CELLULAR
                    ) || hasTransport(
                        NetworkCapabilities.TRANSPORT_ETHERNET
                    )
            }
        } else {
            activeNetworkInfo?.apply {
                result =
                    (type == ConnectivityManager.TYPE_WIFI) || (type == ConnectivityManager.TYPE_MOBILE)
            }
        }
    }
    return result
}