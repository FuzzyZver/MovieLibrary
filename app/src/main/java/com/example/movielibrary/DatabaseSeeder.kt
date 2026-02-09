package com.example.movielibrary

import com.example.movielibrary.data.*
import com.example.movielibrary.data.entities.*
import com.example.movielibrary.data.dao.*

object DatabaseSeeder {

    suspend fun seed(database: AppDatabase) {
        val categoryDao = database.categoryDao()
        val movieDao = database.movieDao()

        if (categoryDao.getAllCategories().isNotEmpty()) return

        val dramaId = insertCategory(categoryDao, "Драма")
        val comedyId = insertCategory(categoryDao, "Комедия")
        val actionId = insertCategory(categoryDao, "Боевик")
        val fantasyId = insertCategory(categoryDao, "Фантастика")

        movieDao.insertMovie(
            MovieEntity(
                title = "Интерстеллар",
                year = 2014,
                country = "США",
                durationMinutes = 169,
                categoryId = fantasyId,
                description = "Фантастический фильм о путешествиях сквозь пространство и время.",
                director = "Кристофер Нолан",
                actors = "Мэттью МакКонахи, Энн Хэтэуэй",
                ageRating = "12+",
                rating = 8.6
            )
        )

        movieDao.insertMovie(
            MovieEntity(
                title = "1+1",
                year = 2011,
                country = "Франция",
                durationMinutes = 112,
                categoryId = dramaId,
                description = "История дружбы между аристократом и его помощником.",
                director = "Оливье Накаш",
                actors = "Франсуа Клюзе, Омар Си",
                ageRating = "12+",
                rating = 8.8
            )
        )

        movieDao.insertMovie(
            MovieEntity(
                title = "Дэдпул",
                year = 2016,
                country = "США",
                durationMinutes = 108,
                categoryId = actionId,
                description = "Антигеройский боевик с юмором.",
                director = "Тим Миллер",
                actors = "Райан Рейнольдс",
                ageRating = "18+",
                rating = 8.0
            )
        )
    }

    private suspend fun insertCategory(
        dao: CategoryDao,
        name: String
    ): Int {
        val category = CategoryEntity(name = name)
        dao.insertCategory(category)
        return dao.getAllCategories().last().id
    }
}
