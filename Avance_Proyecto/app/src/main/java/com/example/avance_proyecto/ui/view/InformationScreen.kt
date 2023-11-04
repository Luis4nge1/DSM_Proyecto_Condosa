package com.example.avance_proyecto.ui.view

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.avance_proyecto.data.TrackingDataSource
import com.example.avance_proyecto.navigation.AppScreen
import com.example.avance_proyecto.ui.viewmodel.InformationViewModel
import com.example.avance_proyecto.ui.theme.ButtonColorDefault
import com.example.avance_proyecto.ui.theme.ButtonColorRed
import com.example.avance_proyecto.ui.theme.TextWhite
import com.example.avance_proyecto.ui.theme.backgroundPrincipal

@Composable
fun InformationScreen2(navController: NavController){
    Scaffold(topBar = {
        TopAppBar(title = {
            Text(text = "Pantalla de solicitudes")
        })
    }) {it->
        println(it)
        BodyContentInformation(navController)
    }
}

@Composable
fun BodyContentInformation(navController: NavController){
    Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
    ){
        Text(text = "¡Bienvenido a la pantalla de solicitudes!")
        Button(onClick = {
            navController.navigate(route = AppScreen.trackingScreen.route)
        }) {
            Text(text = "Seguimiento")
        }
    }
}

@Composable
fun InformationScreen(
        navController: NavController,
        viewModel: InformationViewModel = viewModel(),
        modifier: Modifier = Modifier
){
    val tracking = TrackingDataSource.itemCardInformation

    Scaffold(
            topBar = {
                TrackingAppBarInformation(
                        navigateUp = { navController.navigateUp() }
                )
            }
    ){ innerPadding ->
        LazyColumn(
                modifier = Modifier
                        .padding(innerPadding)
                        .background(backgroundPrincipal)
                        .fillMaxSize()
        ) {
            items(1) {
                tracking.forEach { it->
                    cardInformation(label = stringResource(it.labelData) , data = it.valueData.toString())
                }
                /*cardInformation("Numero de solicitud:", "76")
                cardInformation("Nombre del Solicitante:", "Fabrizzio Quintana")
                cardInformation("Fecha de solicitud:", "14/03/2023")
                cardInformation("Predio:", "Valle las Esmeraldas")
                cardInformation("Area del predio:", "1000 m2")
                cardInformation("N! de Casas-Habitación", "4")
                cardInformation(
                    "Servicio solicitado:",
                    "Administación: 4, Seguridad: 3, Limpieza:2, Jardineria: 1"
                )
                cardInformation("Areas comunes:", "200 m2 total: 2")*/

                ButtonSection(listOf("Áreas Comunes", "Solicitante", "Predio"), "Ver detalles en:")
                ButtonSection(listOf("Cotizar", "Observar", "Anular"), "Seleccionar opción:")
            }
        }
    }

}

@Composable
fun TrackingAppBarInformation(
        navigateUp: () -> Unit,
        modifier: Modifier = Modifier
){
    TopAppBar(
            modifier = Modifier.background(backgroundPrincipal),
            title = {
                Row(
                        modifier = Modifier.fillMaxSize(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                            text = "Información de la solicitud",
                            color = Color.White,
                            fontWeight = FontWeight.Bold
                    )

                }


            },
            colors = TopAppBarDefaults.mediumTopAppBarColors(
                    containerColor = backgroundPrincipal
            ),
            navigationIcon = {
                IconButton(onClick = navigateUp) {
                    Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = "Back",
                            tint = Color.White
                    )
                }
            }
    )
}

@Composable
fun cardInformation(label: String, data: String, modifier: Modifier = Modifier){
    Card(
            modifier = Modifier.padding(vertical = 8.dp, horizontal = 16.dp)
    ){
        Row(
                modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                    text= label,
                    style = MaterialTheme.typography.labelLarge,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(end=16.dp)
            )
            Text(text = data)
        }

    }


}

@Composable
fun ButtonSection(
        botones: List<String>, title: String/*,
    popUpViewModel: PopUpViewModel= viewModel()*/
) {
    val context = LocalContext.current
    //val gameUiState by popUpViewModel.uiState.collectAsState()

    var selectedChipIndex by remember {
        mutableStateOf(0)
    }

    Column(
            modifier = Modifier.padding(vertical = 8.dp, horizontal = 16.dp)
    ){
        Text(
                text=title,
                color= Color.White,
                modifier = Modifier.padding(bottom=10.dp)
        )
        LazyRow(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            items(botones.size) {
                Button(
                        onClick = {
                            showToastInformation(context, "Hola $it")
                            selectedChipIndex = it
                        },
                        /*modifier = Modifier
                            .padding(start = 15.dp, top = 15.dp, bottom = 15.dp),*/
                        colors = ButtonDefaults.buttonColors(
                                if (botones[it].equals("Anular")) {
                                    ButtonColorRed
                                } else {
                                    ButtonColorDefault
                                },
                                Color.White
                        ),
                        shape = MaterialTheme.shapes.small
                ) {
                    Text(text = botones[it], color = TextWhite)
                }
            }
        }
    }
}

fun showToastInformation(context: Context, message: String) {
    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
}
@Preview
@Composable
fun InformationScreenPreview() {
    val navController = rememberNavController() // Importa rememberNavController
    InformationScreen(navController = navController)
}
