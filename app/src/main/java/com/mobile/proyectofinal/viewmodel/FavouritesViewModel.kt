package com.mobile.proyectofinal.viewmodel

import androidx.lifecycle.ViewModel
import com.mobile.proyectofinal.data.NewsRepository
import com.mobile.proyectofinal.data.enitty.News
import kotlinx.coroutines.flow.Flow

class FavouritesViewModel(
    repository: NewsRepository
) : ViewModel() {
    val favouritesNews: Flow<List<News>> = repository.getFavouriteNews()


}