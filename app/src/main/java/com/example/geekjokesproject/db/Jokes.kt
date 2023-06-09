package com.example.geekjokesproject.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "geekJokes")
data class Jokes(
    @PrimaryKey(autoGenerate = true)
    var id:Int,
    @ColumnInfo(name = "jokes")
    var jokes: String?
)