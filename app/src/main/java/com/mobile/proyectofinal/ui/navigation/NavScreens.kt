package com.mobile.proyectofinal.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
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

    data object ReadScreens : NavScreens(
        route = "read",
        title = "Read",
        icon = Icons.Default.CheckCircle
    )

    data object FavouritesScreens : NavScreens(
        route = "favorites",
        title = "Favorites",
        icon = Icons.Default.Favorite
    )
}