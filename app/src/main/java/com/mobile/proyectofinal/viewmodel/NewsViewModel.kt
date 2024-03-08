package com.mobile.proyectofinal.viewmodel


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mobile.proyectofinal.data.NewsRepository
import com.mobile.proyectofinal.model.News
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

//sealed interface MarsUiState {
//  data class Success(val photos: List<News>) : MarsUiState
// object Error : MarsUiState
//object Loading : MarsUiState
//}

class NewsViewModel() : ViewModel() {
    //private val _uiState = MutableLiveData<UiState>(UiState.SignedOut)
    //val uiState: LiveData<UiState>
    //  get() = _uiState
    private val repository = NewsRepository()
    private val _news = MutableLiveData<List<News>>()

    fun getNews(country : String, pageSize : Int): LiveData<List<News>> {
        viewModelScope.launch(Dispatchers.IO) {
            val news = repository.getNews(country, pageSize)
            _news.postValue(news)
        }
        return _news
    }
}