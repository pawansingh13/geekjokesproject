package com.example.geekjokesproject.utill

import com.example.geekjokesproject.db.Jokes

object AppConstants {
    const val OPEN_AGAIN= "openAgain"
    const val APP_SHARED_PREFERENCE = "app_data_geek_jokes"
    const val NO_INTERNET_CONNECTION_AVAILABLE="No Internet Connection Available"
    const val SOMETHING_WENT_WRONG = "Something went wrong"
    const val BASE_URL: String = "https://geek-jokes.sameerkumar.website/"
    var  jokeslist= mutableListOf<Jokes>()

}