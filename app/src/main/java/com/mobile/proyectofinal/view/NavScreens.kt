package com.mobile.proyectofinal.view

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.graphics.vector.ImageVector

sealed class NavScreens(
    val route: String,
    val title: String,
    val icon: ImageVector
) {
    data object HomeScreens : NavScreens(
        route = "home",
        title = "Home",
        icon = Icons.Default.Home
    )

    data object NewsScreens : NavScreens(
        route = "news",
        title = "News",
        icon = Icons.Default.Person
    )

    data object SearchScreens : NavScreens(
        route = "search",
        title = "Search",
        icon = Icons.Default.Settings
    )

    data object FavouritesScreens : NavScreens(
        route = "favourites",
        title = "Favourites",
        icon = Icons.Default.Favorite
    )
}