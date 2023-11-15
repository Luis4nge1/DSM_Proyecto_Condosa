package com.example.avance_proyecto.ui.view

import android.annotation.SuppressLint
import android.graphics.ColorSpace.Rgb
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.avance_proyecto.R
import com.example.avance_proyecto.data.model.ConteoEstadoSolicitudItem
import com.example.avance_proyecto.model.TrackingCard
import com.example.avance_proyecto.navigation.AppScreen
import com.example.avance_proyecto.ui.standardQuadFromTo
import com.example.avance_proyecto.ui.theme.Avance_ProyectoTheme
import com.example.avance_proyecto.ui.theme.Beige1
import com.example.avance_proyecto.ui.theme.Beige2
import com.example.avance_proyecto.ui.theme.Beige3
import com.example.avance_proyecto.ui.theme.BlueViolet1
import com.example.avance_proyecto.ui.theme.BlueViolet2
import com.example.avance_proyecto.ui.theme.BlueViolet3
import com.example.avance_proyecto.ui.theme.ButtonBlue
import com.example.avance_proyecto.ui.theme.LightGreen1
import com.example.avance_proyecto.ui.theme.LightGreen2
import com.example.avance_proyecto.ui.theme.LightGreen3
import com.example.avance_proyecto.ui.theme.OrangeYellow1
import com.example.avance_proyecto.ui.theme.OrangeYellow2
import com.example.avance_proyecto.ui.theme.OrangeYellow3
import com.example.avance_proyecto.ui.theme.TextWhite
import com.example.avance_proyecto.ui.theme.backgroundPrincipal

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomeScreen(navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "MENÚ",
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center,
                        color = Color.White,
                        fontWeight = FontWeight.Bold
                    )
                },
                colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = Color(8,83,148))
            )
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "¡Bienvenido!",
                fontSize = 40.sp,
                color = Color(8,83,148),
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 16.dp)
            )
            Image(
                painter = painterResource(id = R.drawable.logo_condosa),
                contentDescription = null,
                modifier = Modifier.size(300.dp)
            )
            Text(
                text = "Seleccione una opción:",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(top = 16.dp)
            )
            Spacer(modifier = Modifier.height(16.dp))
            GridButtons(navController)
        }
    }
}

@Composable
fun GridButtons(navController: NavController) {
    val buttonColors = ButtonDefaults.buttonColors(Color(8, 83, 148))
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Button(onClick = { navController.navigate(route = AppScreen.trackingScreen.route) },modifier = Modifier.weight(1f),colors = buttonColors) {
                Text("Solicitudes")
            }
            Button(onClick = { /* NADA */ },modifier = Modifier.weight(1f),colors = buttonColors) {
                Text("Solicitantes")
            }
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Button(onClick = { /* NADA */ },modifier = Modifier.weight(1f),colors = buttonColors) {
                Text("Predios")
            }
            Button(onClick = { /* NADA */ },modifier = Modifier.weight(1f),colors = buttonColors) {
                Text("Contratos")
            }
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