package com.example.movielibrary.data.dao

import com.example.movielibrary.data.entities.*
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface HistoryDao {

    @Query("""
        SELECT movies.* FROM movies
        INNER JOIN history ON movies.id = history.movieId
        ORDER BY history.viewedAt DESC
    """)
    suspend fun getHistoryMovies(): List<MovieEntity>

    @Insert
    suspend fun insertHistory(history: HistoryEntity)

    @Query("DELETE FROM history")
    suspend fun clearHistory()
}
