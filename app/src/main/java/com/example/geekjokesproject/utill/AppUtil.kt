package com.example.geekjokesproject.utill


import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.text.format.DateFormat
import java.sql.Timestamp
import java.util.*

object AppUtil {
    fun getDate(time: Long, format: String): String? {
        var date: String = ""
        try {
            val cal: Calendar = Calendar.getInstance(Locale.ENGLISH)
            cal.timeInMillis = time * 1000
            date = DateFormat.format(format, cal).toString()

        } catch (e: Exception) {
            e.printStackTrace()
        }
        return date
    }

    fun getDateFormat(date: Long): Date {
        val ts = Timestamp(date)
        return Date(ts.time)
    }

    fun compareTwoDate(currentDate: Date, sunsetDate: Date) :Boolean{
      return    sunsetDate.before(currentDate)
    }


    public fun checkForInternet(context: Context): Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val network = connectivityManager.activeNetwork ?: return false
            val activeNetwork = connectivityManager.getNetworkCapabilities(network) ?: return false
            return when {
                activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                else -> false
            }
        } else {
            @Suppress("DEPRECATION") val networkInfo =
                connectivityManager.activeNetworkInfo ?: return false
            @Suppress("DEPRECATION")
            return networkInfo.isConnected
        }
    }


}