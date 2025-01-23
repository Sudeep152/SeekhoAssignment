package com.shashank.seekhoassignment.navigation

import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.shashank.seekhoassignment.routes.AppRoutes
import com.shashank.seekhoassignment.screen.AnimeDetailsScreen
import com.shashank.seekhoassignment.screen.TopAnimeListScreen

@Composable
fun MainGraph(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = AppRoutes.TopAnimeListScreen) {
        composable<AppRoutes.TopAnimeListScreen> {
            TopAnimeListScreen(modifier = modifier) {
                navController.navigate(AppRoutes.AnimeDetailsScreen(it))
            }
        }
        composable<AppRoutes.AnimeDetailsScreen> {
            val data = it.toRoute<AppRoutes.AnimeDetailsScreen>()
            AnimeDetailsScreen(modifier = modifier,data.id){
                navController.popBackStack()
            }
            BackHandler {
                navController.popBackStack()
            }
        }
    }

}