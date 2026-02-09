package com.example.movielibrary.data.dao

import com.example.movielibrary.data.entities.CategoryEntity
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface CategoryDao {

    @Query("SELECT * FROM categories")
    suspend fun getAllCategories(): List<CategoryEntity>

    @Insert
    suspend fun insertCategory(category: CategoryEntity)
}
