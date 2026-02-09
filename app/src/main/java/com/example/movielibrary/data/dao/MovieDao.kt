package com.example.movielibrary.data.dao

import com.example.movielibrary.data.entities.MovieEntity
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface MovieDao {

    @Query("SELECT * FROM movies")
    suspend fun getAllMovies(): List<MovieEntity>

    @Query("SELECT * FROM movies WHERE categoryId = :categoryId")
    suspend fun getMoviesByCategory(categoryId: Int): List<MovieEntity>

    @Query("SELECT * FROM movies WHERE title LIKE '%' || :query || '%'")
    suspend fun searchMovies(query: String): List<MovieEntity>

    @Query("SELECT * FROM movies WHERE id = :id")
    suspend fun getMovieById(id: Int): MovieEntity

    @Insert
    suspend fun insertMovie(movie: MovieEntity)
}
