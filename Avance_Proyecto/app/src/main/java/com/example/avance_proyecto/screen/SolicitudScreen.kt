package com.example.avance_proyecto.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import com.example.avance_proyecto.navigation.AppScreen
import androidx.navigation.compose.rememberNavController


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SolicitudScreen(navController: NavController){
    Scaffold(topBar = {
        TopAppBar(title = {
            Text(text = "Pantalla de solicitudes")
        },
            navigationIcon = {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = "Back",
                        tint = Color.Black,
                        modifier = Modifier.clickable{
                            navController.popBackStack()
                        }
                    )
            }
        )
    }) {it->
        println(it)
        BodyContentSolicitud(navController)
    }
}

@Composable
fun BodyContentSolicitud(navController: NavController){
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Text(text = "¡Bienvenido a la pantalla de solicitudes!")
        Button(onClick = {
            navController.navigate(route = AppScreen.informationScreen.route)
        }) {
            Text(text = "Seguimiento")
        }
    }
}

@Preview
@Composable
fun SolicitudScreenPreview() {
    val navController = rememberNavController() // Asegúrate de importar rememberNavController
    SolicitudScreen(navController = navController)
}
