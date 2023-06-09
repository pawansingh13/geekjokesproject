package com.example.geekjokesproject.ui.dashboard.geekjokes.repo

import com.example.geekjokesproject.network.ApiInterface
import com.example.geekjokesproject.network.SafeApiCall
import javax.inject.Inject

class GeekJokesRepo @Inject constructor(
    private val api: ApiInterface,
): SafeApiCall {
    suspend fun getJokes() =
        safeApiCall {
            api.getJokes()
        }


}