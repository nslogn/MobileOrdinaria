package com.mobile.proyectofinal.viewmodel

import androidx.lifecycle.ViewModel
import com.mobile.proyectofinal.data.NewsRepository
import com.mobile.proyectofinal.data.enitty.News
import kotlinx.coroutines.flow.Flow

class ReadViewModel(
    private val repository: NewsRepository
) : ViewModel() {
    val readNews: Flow<List<News>> = repository.getReadNews()

    suspend fun deleteNews(news: News) {
        repository.deleteNews(news)
    }
}