package com.example.geekjokesproject

import android.app.Application
import android.content.Context
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class AppManager : Application() {
    init {
        instance = this
    }
    companion object {
        private var instance: AppManager? = null
        val context: Context? = null
    }
    fun applicationContext(): Context {
        return instance!!.applicationContext
    }
}