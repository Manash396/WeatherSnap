package com.mk.weathersnap.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.BlendMode.Companion.Screen
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.mk.weathersnap.util.ScreenRoute

@Composable
fun WeatherSnapNavHost() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = ScreenRoute.Search.route
    ) {

        composable(ScreenRoute.Search.route) {

        }

        composable(ScreenRoute.Camera.route) {

        }


        composable(ScreenRoute.Reports.route) {

        }

        composable(ScreenRoute.CreateReport.route) {

        }
    }
}