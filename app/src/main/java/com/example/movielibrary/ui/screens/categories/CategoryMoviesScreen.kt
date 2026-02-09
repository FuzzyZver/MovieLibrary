package com.example.movielibrary.ui.screens.categories

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
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

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun CategoryMoviesScreen(
    categoryId: Int,
    repository: MovieRepository,
    onMovieClick: (Int) -> Unit,
    onBack: () -> Unit
) {
    val movies = remember { mutableStateOf<List<MovieEntity>>(emptyList()) }

    LaunchedEffect(categoryId) {
        movies.value = repository.getMoviesByCategory(categoryId)
    }

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = onBack) {
                Icon(Icons.Default.ArrowBack, null)
            }
        }
    ) {
        LazyColumn {
            items(movies.value) { movie ->
                Text(
                    text = movie.title,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { onMovieClick(movie.id) }
                        .padding(16.dp)
                )
            }
        }
    }
}