package com.example.movielibrary.ui.screens.categories

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movielibrary.data.MovieRepository
import com.example.movielibrary.data.entities.CategoryEntity
import kotlinx.coroutines.launch

class CategoriesViewModel(
    private val repository: MovieRepository
) : ViewModel() {

    var categories by mutableStateOf<List<CategoryEntity>>(emptyList())
        private set

    fun loadCategories() {
        viewModelScope.launch {
            categories = repository.getAllCategories()
        }
    }
}
