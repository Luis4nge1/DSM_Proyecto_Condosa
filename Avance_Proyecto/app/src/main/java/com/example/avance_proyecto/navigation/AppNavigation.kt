package com.example.avance_proyecto.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.avance_proyecto.screen.HomeScreen
import com.example.avance_proyecto.screen.InformationScreen
import com.example.avance_proyecto.screen.SolicitudScreen
import com.example.avance_proyecto.screen.TrackingScreen

@Composable
fun AppNavigation(){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = AppScreen.homeScreen.route){
        composable(route = AppScreen.homeScreen.route){
            HomeScreen(navController)
        }
        composable(route = AppScreen.trackingScreen.route ){
            TrackingScreen(navController)
        }
        composable(route = AppScreen.solicitudScreen.route ){
            SolicitudScreen(navController)
        }
        composable(route = AppScreen.informationScreen.route ){
            InformationScreen(navController)
        }
    }
}