package com.example.movielibrary.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movies")
data class MovieEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    val title: String,
    val year: Int,
    val country: String,
    val durationMinutes: Int,
    val categoryId: Int,

    val description: String,

    val director: String,
    val actors: String,
    val ageRating: String,
    val rating: Double
)
