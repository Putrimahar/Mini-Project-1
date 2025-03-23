package com.putrimaharani0087.miniproject1.navigation

sealed class Screen(val route: String){
    data object Home: Screen("mainScreen")
}