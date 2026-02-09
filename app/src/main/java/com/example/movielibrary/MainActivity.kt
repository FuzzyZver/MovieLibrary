package com.example.movielibrary

import androidx.room.*
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.lifecycleScope
import com.example.movielibrary.data.*
import com.example.movielibrary.ui.AppNavHost
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        var database = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java,
            "movies_db"
        ).build()

        var repository = MovieRepository(
            movieDao = database.movieDao(),
            categoryDao = database.categoryDao(),
            historyDao = database.historyDao()
        )

        lifecycleScope.launch {
            DatabaseSeeder.seed(database)
        }

        setContent {
            setContent {
                AppNavHost(repository = repository)
            }
        }
    }
}