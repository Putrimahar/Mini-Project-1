package com.putrimaharani0087.miniproject1.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.putrimaharani0087.miniproject1.MainScreen
import com.putrimaharani0087.miniproject1.ScreenJarak
import com.putrimaharani0087.miniproject1.ScreenKecepatan
import com.putrimaharani0087.miniproject1.ScreenWaktu


@Composable
fun SetupNavGraph(navController: NavHostController = rememberNavController()) {
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route
    ) {
        composable(route = Screen.Home.route) {
            MainScreen(navController)
        }
        composable("kecepatan") {
            ScreenKecepatan(navController)
        }
        composable("Jarak") {
            ScreenJarak(navController)
        }
        composable("Waktu") {
            ScreenWaktu(navController)
        }
        composable(route = Screen.Rumus.route) {
            RumusScreen(navController)
        }
    }
}
