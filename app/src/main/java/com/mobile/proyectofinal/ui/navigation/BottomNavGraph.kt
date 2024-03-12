package com.mobile.proyectofinal.ui.navigation

import android.net.Uri
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
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
            HomeScreen { newsUrl ->
                navController.navigate("${NavScreens.NewsScreens.route}/${Uri.encode(newsUrl)}")
            }
        }

        composable(route = NavScreens.FavouritesScreens.route) {
            FavouritesScreen()
        }
        composable(
            route = "${NavScreens.NewsScreens.route}/{newsUrl}",
            arguments = listOf(navArgument("newsUrl") {
                type = NavType.StringType
            })
        ) { backStackEntry ->
            val newsUrl = backStackEntry.arguments?.getString("newsUrl")
            NewsScreen(newsUrl = newsUrl)
        }
        composable(route = NavScreens.SearchScreens.route) {
            SearchScreen()
        }
    }
}