package com.mobile.proyectofinal

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.mobile.proyectofinal.viewmodel.FavouritesViewModel
import com.mobile.proyectofinal.viewmodel.HomeNewsViewModel

object AppViewModelProvider {

    val Factory = viewModelFactory {
        initializer {
            HomeNewsViewModel(NewsApplication().container.newsRepository)
        }

        initializer {
            FavouritesViewModel(NewsApplication().container.newsRepository)
        }

    }
}

fun CreationExtras.NewsApplication(): NewsApplication =
    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as NewsApplication)
//APPLICATION_KEY = A CreationExtras.Key to query an application in which ViewModel is being created.
