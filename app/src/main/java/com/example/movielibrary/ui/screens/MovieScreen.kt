package com.example.movielibrary.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.movielibrary.data.MovieRepository
import com.example.movielibrary.data.entities.MovieEntity

@Composable
fun MovieScreen(
    movieId: Int,
    repository: MovieRepository,
    onBack: () -> Unit
) {
    val movie = remember { mutableStateOf<MovieEntity?>(null) }

    LaunchedEffect(movieId) {
        movie.value = repository.getMovieById(movieId)
        repository.addToHistory(movieId)
    }

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = onBack) {
                Icon(Icons.Default.ArrowBack, null)
            }
        }
    ) { padding ->
        movie.value?.let {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(it.title, style = MaterialTheme.typography.headlineMedium)
                Text("Год: ${it.year}")
                Text("Страна: ${it.country}")
                Text("Длительность: ${it.durationMinutes} мин")
                Text("Описание: ${it.description}")
            }
        }
    }
}
