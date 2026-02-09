package com.example.movielibrary.data

import com.example.movielibrary.data.entities.*
import com.example.movielibrary.data.dao.*
import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [
        MovieEntity::class,
        CategoryEntity::class,
        HistoryEntity::class
    ],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun movieDao(): MovieDao
    abstract fun categoryDao(): CategoryDao
    abstract fun historyDao(): HistoryDao
}