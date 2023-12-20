package dev.jay.wouldurather.utilities

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import kotlin.random.Random

object CoreUtility {

    const val TAG = "WouldURatherGame"
    fun isInternetConnected(context: Context): Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkCapabilities = connectivityManager.activeNetwork ?: return false
        val actNw = connectivityManager.getNetworkCapabilities(networkCapabilities) ?: return false
        val result = when {
            actNw.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
            actNw.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
            actNw.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
            else -> false
        }
        return result
    }

    fun getRandomQuestion(): Int {
        return Random.nextInt(1, 587)
    }
}