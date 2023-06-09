package com.example.geekjokesproject.ui.dashboard.geekjokes.viewmodel


import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.geekjokesproject.db.AppDatabase
import com.example.geekjokesproject.db.DatabaseBuilder
import com.example.geekjokesproject.db.Jokes
import com.example.geekjokesproject.network.Resource
import com.example.geekjokesproject.ui.dashboard.geekjokes.model.GeekJokesResponse
import com.example.geekjokesproject.ui.dashboard.geekjokes.repo.GeekJokesRepo

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GeekJokesViewModel @Inject constructor(private val repository: GeekJokesRepo) : ViewModel() {
    var _getJokesResponse: MutableLiveData<Resource<GeekJokesResponse>> =
        MutableLiveData()
    val getJokesResponse: LiveData<Resource<GeekJokesResponse>>
        get() = _getJokesResponse

    fun getJokes() = viewModelScope.launch {
        _getJokesResponse.value = Resource.Loading
        _getJokesResponse.value = repository.getJokes()
    }

    fun geekJokesStoreInDB(jokes: Jokes, context: Context) {
        viewModelScope.launch {
            var db: AppDatabase = DatabaseBuilder.getInstance(context)
            db.geekJokesDao().insertAll(jokes)
        }
    }

      var _getAllJokesList: MutableLiveData<List<Jokes>> =
        MutableLiveData()

    fun getAllJokesList(context: Context) {
        viewModelScope.launch {
            var db: AppDatabase = DatabaseBuilder.getInstance(context)
            _getAllJokesList.value = db.geekJokesDao().getAll()
        }
    }
}