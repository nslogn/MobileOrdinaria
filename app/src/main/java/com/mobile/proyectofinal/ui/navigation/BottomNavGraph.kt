package com.mobile.proyectofinal.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.mobile.proyectofinal.ui.screens.FavouritesScreen
import com.mobile.proyectofinal.ui.screens.HomeScreen
import com.mobile.proyectofinal.ui.screens.NewsScreen
import com.mobile.proyectofinal.ui.screens.SearchScreen

@Composable
fun BottomNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = NavScreens.HomeScreens.route
    ) {
        composable(route = NavScreens.HomeScreens.route) {
            HomeScreen()
        }
        composable(route = NavScreens.SearchScreens.route) {
            SearchScreen()
        }
        composable(route = NavScreens.NewsScreens.route) {
            NewsScreen()
        }
        composable(route = NavScreens.FavouritesScreens.route){
            FavouritesScreen()
        }
    }
}