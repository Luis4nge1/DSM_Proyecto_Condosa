package com.example.avance_proyecto.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.avance_proyecto.navigation.AppScreen

@Composable
fun HomeScreen(navController: NavController){
    Scaffold(topBar = {
        TopAppBar(title = {
            Text(text = "Pantalla Principal")
        })
    }) {it->
        println(it)
        BodyContentHome(navController)
    }
}

@Composable
fun BodyContentHome(navController: NavController){
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Text(text = "¡Bienvenido a la pantalla principal!")
        Button(onClick = {
            navController.navigate(route = AppScreen.trackingScreen.route)
        }) {
            Text(text = "Seguimiento")
        }
    }
}