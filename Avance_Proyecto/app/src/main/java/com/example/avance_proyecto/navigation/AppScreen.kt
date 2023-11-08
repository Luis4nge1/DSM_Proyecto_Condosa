package com.example.avance_proyecto.navigation

sealed class AppScreen(val route: String){
    object inicioScreen: AppScreen("inicio")
    object homeScreen: AppScreen("home")
    object trackingScreen: AppScreen("tracking")
    object solicitudScreen: AppScreen("solicitud")
    object informationScreen: AppScreen("information")
    object areaComunScreen: AppScreen("AreaComunInfo")
    object anulacionScreen: AppScreen("AnulacionInfo")
}
