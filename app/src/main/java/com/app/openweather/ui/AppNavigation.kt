package com.app.openweather.ui

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.app.openweather.ui.home.HomeScreen
import com.app.openweather.ui.splash.SplashScreen
import com.app.openweather.ui.weather.WeatherScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "splash") {
        composable("splash") {
            SplashScreen(navController)
        }
        composable("home") {
            HomeScreen(navController)
        }
        composable("weather/{city}") { backStackEntry ->
            val city = backStackEntry.arguments?.getString("city")
            WeatherScreen(city = city, navController = navController)
        }
    }
}