package com.mobile.proyectofinal.viewmodel


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mobile.proyectofinal.data.NewsRepository
import com.mobile.proyectofinal.data.SettingsDataStore
import com.mobile.proyectofinal.data.enitty.News
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class HomeViewModel(
    private val repository: NewsRepository,
    private val settingsManager: SettingsDataStore
) : ViewModel() {
    private val _news = MutableLiveData<List<News>>()

    fun getNews(country: String, pageSize: Int): LiveData<List<News>> {
        viewModelScope.launch(Dispatchers.IO) {
            val news = repository.getNews(country, pageSize)
            _news.postValue(news)
        }
        return _news
    }

    val countryFlow: Flow<String> = settingsManager.countryFlow

    suspend fun updateCountry(country: String) {
        settingsManager.updateCountry(country)
    }

    suspend fun insertFavouriteNew(news: News) {
        repository.insertFavouriteNew(news)
    }
}