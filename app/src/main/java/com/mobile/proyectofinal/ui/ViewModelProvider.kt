package com.mobile.proyectofinal.ui

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.mobile.proyectofinal.NewsApplication
import com.mobile.proyectofinal.viewmodel.HomeNewsViewModel

object ViewModelProvider {

    val Factory = viewModelFactory {
        initializer {
            HomeNewsViewModel(NewsApplication().container.newsRepository)
        }


    }
}

fun CreationExtras.NewsApplication(): NewsApplication =
    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as NewsApplication)
