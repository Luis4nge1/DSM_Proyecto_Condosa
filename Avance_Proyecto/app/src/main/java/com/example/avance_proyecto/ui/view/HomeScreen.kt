package com.example.avance_proyecto.ui.view

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
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.avance_proyecto.navigation.AppScreen
import com.example.avance_proyecto.ui.theme.Avance_ProyectoTheme

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

@Preview
@Composable
fun HomeScreenPreview() {
    Avance_ProyectoTheme(darkTheme = false) {
        val navController = rememberNavController()
        HomeScreen(navController = navController)
    }
}