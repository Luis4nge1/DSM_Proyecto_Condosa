package com.example.avance_proyecto.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.avance_proyecto.ui.view.AnimatedSplashScreen
import com.example.avance_proyecto.ui.view.HomeScreen
import com.example.avance_proyecto.ui.view.InformationScreen
import com.example.avance_proyecto.ui.view.SolicitudScreen
import com.example.avance_proyecto.ui.view.TrackingScreen

@Composable
fun AppNavigation(){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = AppScreen.splahScreen.route){
        composable(route = AppScreen.splahScreen.route) {
            AnimatedSplashScreen(navController)
        }
        composable(route = AppScreen.homeScreen.route){
            HomeScreen(navController)
        }
        composable(route = AppScreen.trackingScreen.route ){
            TrackingScreen(navController)
        }
        composable(route = AppScreen.solicitudScreen.route+"/{body}" ){
            val body = it.arguments?.getString("body") ?: "0"
            SolicitudScreen(navController,body)
        }
        composable(route = AppScreen.informationScreen.route +"/{body}"){
            val body = it.arguments?.getString("body") ?: "0"
            InformationScreen(navController,body)
            println("\nApp navigation id solicitud: $body")
        }
    }
}