package com.example.geekjokesproject.utill

import android.content.Context
import android.content.SharedPreferences

object AppPreference {

    private lateinit var appPreference: SharedPreferences

    /** Initialize the club app preferences using app [context] */
    fun init(context: Context) {
        appPreference =
            context.getSharedPreferences(AppConstants.APP_SHARED_PREFERENCE, Context.MODE_PRIVATE)
    }

    fun writeToSharedPreferences(key: String, value: Any?) = with(appPreference.edit()) {
        when (value) {
            is Int -> putInt(key, value)
            is String -> putString(key, value)
            is Boolean -> putBoolean(key, value)
            else -> throw IllegalArgumentException("Shared preference only accept Primitive data types")

        }.apply()
    }

    fun getBooleanValue(key: String) = appPreference.getBoolean(key, false)
    fun getStringValue(key: String) = appPreference.getString(key, null)
    fun getIntValue(key: String, default: Int) = appPreference.getInt(key, default)

    /** Clear the app preference */
    fun clearData() = appPreference.edit().clear().apply()


}