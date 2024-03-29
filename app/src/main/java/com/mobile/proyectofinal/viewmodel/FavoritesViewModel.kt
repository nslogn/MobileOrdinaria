package com.mobile.proyectofinal.viewmodel

import androidx.lifecycle.ViewModel
import com.mobile.proyectofinal.data.NewsRepository
import com.mobile.proyectofinal.data.enitty.News
import kotlinx.coroutines.flow.Flow

class FavoritesViewModel(
    private val repository: NewsRepository
) : ViewModel() {
    val favouritesNews: Flow<List<News>> = repository.getFavouriteNews()

    suspend fun deleteNews(news: News){
        repository.deleteNews(news)
    }
}