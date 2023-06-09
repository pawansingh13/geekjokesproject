package com.example.geekjokesproject.db

import androidx.room.*
import androidx.room.OnConflictStrategy.IGNORE

@Dao
interface GeekJokesDao {

     @Query("Select * From geekJokes")
     suspend fun getAll(): List<Jokes>

    @Insert(onConflict = IGNORE)
    suspend fun insertAll(jokes:  Jokes)

    @Delete
    suspend fun delete(jokes: Jokes)
}