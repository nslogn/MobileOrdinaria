package com.mobile.proyectofinal.composables.screens

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.graphics.vector.ImageVector

sealed class NavScreen(
    val route: String,
    val title: String,
    val icon: ImageVector
) {
    object HomeScreen : NavScreen(
        route = "home",
        title = "Home",
        icon = Icons.Default.Home
    )

    object NewsScreen : NavScreen(
        route = "news",
        title = "News",
        icon = Icons.Default.Person
    )

    object SearchScreen : NavScreen(
        route = "search",
        title = "Search",
        icon = Icons.Default.Settings
    )

    object FavouritesScreen : NavScreen(
        route = "favourites",
        title = "Favourites",
        icon = Icons.Default.Favorite
    )
}