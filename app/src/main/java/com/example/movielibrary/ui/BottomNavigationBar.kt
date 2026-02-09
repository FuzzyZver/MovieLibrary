package com.example.movielibrary.ui

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable


@Composable
fun BottomNavigationBar(
    currentScreen: String,
    onNavigate: (String) -> Unit
) {
    NavigationBar {
        NavigationBarItem(
            selected = currentScreen == Routes.CATEGORIES,
            onClick = { onNavigate(Routes.CATEGORIES) },
            icon = { Icon(Icons.Default.List, null) },
            label = { Text("Категории") }
        )
        NavigationBarItem(
            selected = currentScreen == Routes.HOME,
            onClick = { onNavigate(Routes.HOME) },
            icon = { Icon(Icons.Default.Home, null) },
            label = { Text("Главная") }
        )
        NavigationBarItem(
            selected = currentScreen == Routes.HISTORY,
            onClick = { onNavigate(Routes.HISTORY) },
            icon = { Icon(Icons.Default.DateRange, null) },
            label = { Text("История") }
        )
    }
}
