package com.example.avance_proyecto.ui.view

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.avance_proyecto.data.model.InformacionPredioItem
import com.example.avance_proyecto.ui.theme.Avance_ProyectoTheme
import com.example.avance_proyecto.ui.theme.backgroundPrincipal
import com.example.avance_proyecto.ui.viewmodel.InformacionPredioViewModel


@Composable
fun PredioContent(
    idPredio:String,
    onDimiss: ()->Unit,
    properties: DialogProperties=DialogProperties()
) {

    val informacionPredioViewModel: InformacionPredioViewModel = viewModel()
    informacionPredioViewModel.getInformacionPredio(idPredio)

    val isLoading by informacionPredioViewModel.isLoading.collectAsState()

    val popupWidth = 320.dp
    val popupHeight = 500.dp

    Dialog(
        onDismissRequest = { onDimiss() },
        properties = properties
    ) {
        Scaffold(
            modifier = Modifier
                .background(backgroundPrincipal)
                .width(popupWidth)
                .height(popupHeight)
        ) { innerPadding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .background(backgroundPrincipal)
            ) {
                // Usa TopAppBar en lugar de Row con IconButton y Text
                TopAppBar(
                    title = {
                        Text(
                            text = "Información del Predio",
                            color = Color.White,
                            style = MaterialTheme.typography.titleMedium.copy(
                                fontWeight = FontWeight.Bold
                            )
                        )
                    },
                    navigationIcon = {
                        CircleAButton(onDimiss)
                    },
                    modifier = Modifier.background(backgroundPrincipal),
                    colors = TopAppBarDefaults.mediumTopAppBarColors(
                        containerColor = backgroundPrincipal
                    ),
                )
                if(isLoading){
                    Column(
                        modifier = Modifier
                            .fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ){
                        CircularProgressIndicator(
                            color = Color.White,
                            modifier = Modifier.size(50.dp)
                        )
                    }
                }
                else {
                    val listInformacionSolicitante by informacionPredioViewModel.idPredioResult.collectAsState()

                    PredioDetailsContent(
                        listInformacionSolicitante,
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp)
                    )
                }
            }
        }
    }

}

@Composable
fun PredioDetailsContent(list: List<InformacionPredioItem>,modifier: Modifier = Modifier) {
    // Lista de datos para los detalles del solicitante
    val solicitantes = list

    LazyColumn(
        modifier = modifier
            .background(Color.Gray)
            .padding(16.dp)
    ) {
        items(solicitantes) { solicitante ->
            PredioCard(solicitantes)
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

@Composable
fun PredioCard(list: List<InformacionPredioItem>) {
    val popupWidth = 250.dp
    Card(
        modifier = Modifier
            .width(popupWidth)
            .padding(4.dp)
    ) {
        // Card para el nombre del solicitante
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
                .padding(6.dp)
            //.padding(bottom = 8.dp) // Agrega espacio entre las cards
        ) {
            Column(
                modifier = Modifier
                    .padding(8.dp)
            ) {
                Text(
                    text = "Nombre: ${list.firstOrNull()?.nombrePredio} ",
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontWeight = FontWeight.Bold
                    )
                )
            }
        }

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
                .padding(6.dp)
            //.padding(bottom = 8.dp) // Agrega espacio entre las cards
        ) {
            Column(
                modifier = Modifier
                    .padding(8.dp)
            ) {
                Text(
                    text = "RUC: ${list.firstOrNull()?.ruc}",
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontWeight = FontWeight.Bold
                    )
                )
            }
        }

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
                .padding(6.dp)
            //.padding(bottom = 8.dp) // Agrega espacio entre las cards
        ) {
            Column(
                modifier = Modifier
                    .padding(8.dp)
            ) {
                Text(
                    text = "Tipo Condominio: ${list.firstOrNull()?.tipoPredio}",
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontWeight = FontWeight.Bold
                    )
                )
            }
        }

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
                .padding(6.dp)
            //.padding(bottom = 8.dp) // Agrega espacio entre las cards
        ) {
            Column(
                modifier = Modifier
                    .padding(8.dp)
            ) {
                Text(
                    text = "Ubicacion: ${list.firstOrNull()?.departamento} - ${list.firstOrNull()?.provincia}",
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontWeight = FontWeight.Bold
                    )
                )
            }
        }

        // Card para el número de documento
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
                .padding(6.dp)
            //.padding(bottom = 8.dp) // Agrega espacio entre las cards
        ) {
            Column(
                modifier = Modifier
                    .padding(8.dp)
            ) {
                Text(
                    text = "N° de Contacto: ${list.firstOrNull()?.telefono}",
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontWeight = FontWeight.Bold
                    )
                )
            }
        }

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
                .padding(6.dp)
            //.padding(bottom = 8.dp) // Agrega espacio entre las cards
        ) {
            Column(
                modifier = Modifier
                    .padding(8.dp)
            ) {
                Text(
                    text = "Dirección: ${list.firstOrNull()?.direccion}",
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontWeight = FontWeight.Bold
                    )
                )
            }
        }

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
                .padding(6.dp)
            //.padding(bottom = 8.dp) // Agrega espacio entre las cards
        ) {
            Column(
                modifier = Modifier
                    .padding(8.dp)
            ) {
                Text(
                    text = "Correo: ${list.firstOrNull()?.correo}",
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontWeight = FontWeight.Bold
                    )
                )
            }
        }

    }
}



@Composable
fun CircleAButton(onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .size(48.dp)
            .padding(8.dp)
            .clickable { onClick() }
            .background(Color.White, shape = CircleShape)
    ) {
        Icon(
            imageVector = Icons.Default.Close,
            contentDescription = "Cerrar",
            tint = Color.Black,
            modifier = Modifier
                .padding(8.dp)
                .size(24.dp)
        )
    }
}
/*
@Preview
@Composable
fun PredioContent() {
    Avance_ProyectoTheme {
        SolicitanteContent(onDimiss = {})
    }
}*/