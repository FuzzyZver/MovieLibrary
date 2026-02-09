package com.example.movielibrary.ui.screens.history

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movielibrary.data.MovieRepository
import com.example.movielibrary.data.entities.MovieEntity
import kotlinx.coroutines.launch

class HistoryViewModel(
    private val repository: MovieRepository
) : ViewModel() {

    var history by mutableStateOf<List<MovieEntity>>(emptyList())
        private set

    fun loadHistory() {
        viewModelScope.launch {
            history = repository.getHistory()
        }
    }

    fun clearHistory() {
        viewModelScope.launch {
            repository.clearHistory()
            history = emptyList()
        }
    }
}
