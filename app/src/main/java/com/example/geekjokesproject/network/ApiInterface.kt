package com.example.geekjokesproject.network

import com.example.geekjokesproject.ui.dashboard.geekjokes.model.GeekJokesResponse
import retrofit2.http.GET


interface ApiInterface {
    @GET("api?format=json")
    suspend fun getJokes(): GeekJokesResponse
}