package com.example.geekjokesproject.db

import androidx.room.Database
import androidx.room.RoomDatabase


@Database(entities = [Jokes::class], version = 1)

abstract class AppDatabase : RoomDatabase() {
    abstract fun geekJokesDao(): GeekJokesDao
}