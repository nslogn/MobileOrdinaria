package com.mobile.proyectofinal.composables.screens

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.mobile.proyectofinal.composables.screens.navScreens.FavouritesScreen
import com.mobile.proyectofinal.composables.screens.navScreens.HomeScreen
import com.mobile.proyectofinal.composables.screens.navScreens.NewsScreen
import com.mobile.proyectofinal.composables.screens.navScreens.SearchScreen

@Composable
fun BottomNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = NavScreen.HomeScreen.route
    ) {
        composable(route = NavScreen.HomeScreen.route) {
            HomeScreen()
        }
        composable(route = NavScreen.SearchScreen.route) {
            SearchScreen()
        }
        composable(route = NavScreen.NewsScreen.route) {
            NewsScreen()
        }
        composable(route = NavScreen.FavouritesScreen.route){
            FavouritesScreen()
        }
    }
}