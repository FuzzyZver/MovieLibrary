package com.example.movielibrary.data

import com.example.movielibrary.data.dao.*
import com.example.movielibrary.data.entities.*

class MovieRepository(
    private val movieDao: MovieDao,
    private val categoryDao: CategoryDao,
    private val historyDao: HistoryDao
) {

    /* ---------- Movies ---------- */

    suspend fun getRecommendedMovies(): List<MovieEntity> {
        return movieDao.getAllMovies()
    }

    suspend fun getMovieById(id: Int): MovieEntity {
        return movieDao.getMovieById(id)
    }

    suspend fun getMoviesByCategory(categoryId: Int): List<MovieEntity> {
        return movieDao.getMoviesByCategory(categoryId)
    }

    suspend fun searchMovies(query: String): List<MovieEntity> {
        return movieDao.searchMovies(query)
    }

    /* ---------- Categories ---------- */

    suspend fun getAllCategories(): List<CategoryEntity> {
        return categoryDao.getAllCategories()
    }

    /* ---------- History ---------- */

    suspend fun addToHistory(movieId: Int) {
        historyDao.insertHistory(
            HistoryEntity(
                movieId = movieId,
                viewedAt = System.currentTimeMillis()
            )
        )
    }

    suspend fun getHistory(): List<MovieEntity> {
        return historyDao.getHistoryMovies()
    }

    suspend fun clearHistory() {
        historyDao.clearHistory()
    }
}
