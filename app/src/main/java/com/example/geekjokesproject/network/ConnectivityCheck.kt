package com.example.geekjokesproject.network

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkCapabilities

class ConnectivityCheck(private val connectivityCallBack: ConnectivityCallback) :
    BroadcastReceiver() {

    interface ConnectivityCallback {
        fun turnedOn()
        fun turnedOff()
    }

    override fun onReceive(context: Context, intent: Intent?) {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val nw = connectivityManager.activeNetwork
        val actNw = connectivityManager.getNetworkCapabilities(nw)
        if (actNw == null || nw == null) {
            connectivityCallBack.turnedOff()
            return
        }
        when {
            actNw.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> connectivityCallBack.turnedOn()
            actNw.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> connectivityCallBack.turnedOn()
            else -> connectivityCallBack.turnedOff()
        }
    }
}