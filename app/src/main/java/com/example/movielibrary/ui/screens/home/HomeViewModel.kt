package com.example.movielibrary.ui.screens.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movielibrary.data.MovieRepository
import com.example.movielibrary.data.entities.MovieEntity
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class HomeViewModel(
    private val repository: MovieRepository
) : ViewModel() {

    var movies by mutableStateOf<List<MovieEntity>>(emptyList())
        private set

    var searchQuery by mutableStateOf("")
        private set

    fun loadMovies() {
        viewModelScope.launch {
            movies = repository.getRecommendedMovies()
        }
    }

    private var searchJob: Job? = null

    fun onSearchChange(query: String) {
        searchQuery = query
        searchJob?.cancel()

        searchJob = viewModelScope.launch {
            delay(300)
            movies = if (query.isBlank()) {
                repository.getRecommendedMovies()
            } else {
                repository.searchMovies(query)
            }
        }
    }
}

