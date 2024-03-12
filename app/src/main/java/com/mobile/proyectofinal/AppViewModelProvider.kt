package com.mobile.proyectofinal

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.mobile.proyectofinal.viewmodel.FavoritesViewModel
import com.mobile.proyectofinal.viewmodel.HomeViewModel
import com.mobile.proyectofinal.viewmodel.SearchViewModel

object AppViewModelProvider {

    val Factory = viewModelFactory {
        initializer {
            HomeViewModel(
                NewsApplication().container.newsRepository,
                NewsApplication().container.settingDataStore
            )
        }

        initializer {
            FavoritesViewModel(NewsApplication().container.newsRepository)
        }

        initializer {
            SearchViewModel(NewsApplication().container.newsRepository)
        }

    }
}

fun CreationExtras.NewsApplication(): NewsApplication =
    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as NewsApplication)
//APPLICATION_KEY = A CreationExtras.Key to query an application in which ViewModel is being created.
