package com.example.movielibrary.ui

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.movielibrary.data.MovieRepository
import com.example.movielibrary.ui.screens.MovieScreen
import com.example.movielibrary.ui.screens.categories.CategoriesScreen
import com.example.movielibrary.ui.screens.categories.CategoriesViewModel
import com.example.movielibrary.ui.screens.categories.CategoryMoviesScreen
import com.example.movielibrary.ui.screens.history.HistoryScreen
import com.example.movielibrary.ui.screens.history.HistoryViewModel
import com.example.movielibrary.ui.screens.home.HomeScreen
import com.example.movielibrary.ui.screens.home.HomeViewModel

object Routes {
    const val HOME = "home"
    const val CATEGORIES = "categories"
    const val HISTORY = "history"
    const val CATEGORY = "category"
    const val MOVIE = "movie"
}

@Composable
fun BottomNavigationBar(
    currentScreen: String?,
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

@Composable
fun AppNavHost(repository: MovieRepository) {
    val navController = rememberNavController()

    Scaffold(
        bottomBar = {
            val navBackStackEntry by navController.currentBackStackEntryAsState()
            val currentDestination = navBackStackEntry?.destination?.route

            // Список маршрутов, где должна быть видна нижняя панель
            val rootRoutes = listOf(Routes.HOME, Routes.CATEGORIES, Routes.HISTORY)

            if (currentDestination in rootRoutes) {
                BottomNavigationBar(
                    currentScreen = currentDestination,
                    onNavigate = { route ->
                        navController.navigate(route) {
                            // Очищаем стек до стартового экрана, чтобы не копить вкладки
                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState = true
                            }
                            // Избегаем создания копии экрана, если мы уже на нем
                            launchSingleTop = true
                            // Восстанавливаем состояние (скролл и т.д.) при возврате на вкладку
                            restoreState = true
                        }
                    }
                )
            }
        }
    ) { padding ->
        NavHost(
            navController = navController,
            startDestination = Routes.HOME,
            modifier = Modifier.padding(padding)
        ) {
            composable(Routes.HOME) {
                val viewModel: HomeViewModel = viewModel(factory = factoryFor { HomeViewModel(repository) })
                HomeScreen(
                    viewModel = viewModel,
                    onMovieClick = { movieId -> navController.navigate("${Routes.MOVIE}/$movieId") }
                )
            }

            composable(Routes.CATEGORIES) {
                val viewModel: CategoriesViewModel = viewModel(factory = factoryFor { CategoriesViewModel(repository) })
                CategoriesScreen(
                    viewModel = viewModel,
                    onCategoryClick = { categoryId -> navController.navigate("${Routes.CATEGORY}/$categoryId") }
                )
            }

            composable(Routes.HISTORY) {
                val viewModel: HistoryViewModel = viewModel(factory = factoryFor { HistoryViewModel(repository) })
                HistoryScreen(
                    viewModel = viewModel,
                    onMovieClick = { movieId -> navController.navigate("${Routes.MOVIE}/$movieId") }
                )
            }

            composable(
                route = "${Routes.CATEGORY}/{categoryId}",
                arguments = listOf(navArgument("categoryId") { type = NavType.IntType })
            ) { backStackEntry ->
                val categoryId = backStackEntry.arguments!!.getInt("categoryId")
                CategoryMoviesScreen(
                    categoryId = categoryId,
                    repository = repository,
                    onMovieClick = { movieId -> navController.navigate("${Routes.MOVIE}/$movieId") },
                    onBack = { navController.popBackStack() }
                )
            }

            composable(
                route = "${Routes.MOVIE}/{movieId}",
                arguments = listOf(navArgument("movieId") { type = NavType.IntType })
            ) { backStackEntry ->
                val movieId = backStackEntry.arguments!!.getInt("movieId")
                MovieScreen(
                    movieId = movieId,
                    repository = repository,
                    onBack = { navController.popBackStack() }
                )
            }
        }
    }
}

/**
 * Вспомогательная функция для создания фабрики ViewModel.
 * Чтобы не писать по 10 строк кода для каждой ViewModel.
 */
inline fun <reified T : ViewModel> factoryFor(crossinline create: () -> T): ViewModelProvider.Factory {
    return object : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return create() as T
        }
    }
}