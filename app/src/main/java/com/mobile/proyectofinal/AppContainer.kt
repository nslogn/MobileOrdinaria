package com.mobile.proyectofinal

import android.content.Context
import com.mobile.proyectofinal.model.NewsRepository
import com.mobile.proyectofinal.model.db.NewsDatabase

interface AppContainer {
    val newsRepository: NewsRepository
}

/**
 * [AppContainer] implementation that provides instance of [NewsRepository]
 */
class AppDataContainer(private val context: Context) : AppContainer {

    override val newsRepository: NewsRepository by lazy {
        NewsRepository(NewsDatabase.getDatabase(context).newsDao())
    }
}