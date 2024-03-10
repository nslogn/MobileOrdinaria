package com.mobile.proyectofinal.viewmodel


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mobile.proyectofinal.model.NewsRepository
import com.mobile.proyectofinal.model.enitty.News
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeNewsViewModel(
    private val repository: NewsRepository
) : ViewModel() {
    private val _news = MutableLiveData<List<News>>()

    fun getNews(country: String, pageSize: Int): LiveData<List<News>> {
        viewModelScope.launch(Dispatchers.IO) {
            val news = repository.getNews(country, pageSize)
            _news.postValue(news)
        }
        return _news
    }

    suspend fun insertFavouriteNew(news: News) {
        repository.insertFavouriteNew(news)
    }
}