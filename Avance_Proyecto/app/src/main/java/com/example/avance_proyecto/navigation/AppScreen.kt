package com.example.avance_proyecto.navigation

sealed class AppScreen(val route: String){
    object inicioScreen: AppScreen("first_screen")
    object homeScreen: AppScreen("second_screen")
    object trackingScreen: AppScreen("third_screen")
    object solicitudScreen: AppScreen("fourth_screen")
    object informationScreen: AppScreen("fifth_screen")
}
