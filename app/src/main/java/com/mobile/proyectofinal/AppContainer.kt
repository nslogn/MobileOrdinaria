package com.mobile.proyectofinal

import android.content.Context
import com.mobile.proyectofinal.data.NewsRepository
import com.mobile.proyectofinal.data.SettingsDataStore
import com.mobile.proyectofinal.data.db.NewsDatabase

interface AppContainer {
    val newsRepository: NewsRepository
    val settingDataStore: SettingsDataStore
}

/**
 * [AppContainer] implementation that provides instance of [NewsRepository]
 */
class AppDataContainer(private val context: Context) : AppContainer {

    override val newsRepository: NewsRepository by lazy {
        NewsRepository(NewsDatabase.getDatabase(context).newsDao())
    }
    override val settingDataStore: SettingsDataStore by lazy{
        SettingsDataStore(context)
    }
}
