package com.example.movielibrary.ui.screens.history

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun HistoryScreen(
    viewModel: HistoryViewModel,
    onMovieClick: (Int) -> Unit
) {
    LaunchedEffect(Unit) {
        viewModel.loadHistory()
    }

    Column {
        Button(
            onClick = { viewModel.clearHistory() },
            modifier = Modifier.padding(16.dp)
        ) {
            Text("Очистить историю")
        }

        LazyColumn {
            items(viewModel.history) { movie ->
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

