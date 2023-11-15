package com.example.avance_proyecto.ui.view

import android.annotation.SuppressLint
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
import com.example.avance_proyecto.data.model.AreasComunesItem
import com.example.avance_proyecto.data.model.InformacionPredioItem
import com.example.avance_proyecto.ui.theme.Avance_ProyectoTheme
import com.example.avance_proyecto.ui.theme.backgroundPrincipal
import com.example.avance_proyecto.ui.viewmodel.AreasComunesViewModel
import com.example.avance_proyecto.ui.viewmodel.InformacionPredioViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun InformacionAreasComunesScreen(
    idPredio:String,
    onDimiss: ()->Unit,
    properties: DialogProperties=DialogProperties()
) {
    val areasComunesViewModel: AreasComunesViewModel = viewModel()
    areasComunesViewModel.getAreasComunes(idPredio)

    val isLoading by areasComunesViewModel.isLoading.collectAsState()

    val popupWidth = 320.dp
    val popupHeight = 350.dp

    Dialog(
        onDismissRequest = { onDimiss() },
        properties = properties
    ){
        Scaffold(
            modifier = Modifier
                //.fillMaxSize()
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
                            text = "Información de áreas comunes",
                            color = Color.White,
                            style = MaterialTheme.typography.titleMedium.copy(
                                fontWeight = FontWeight.Bold
                            )
                        )
                    },
                    navigationIcon = {
                        CircleCloseButton(onDimiss)
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
                    val listInformacionSolicitante by areasComunesViewModel.idPredioResult.collectAsState()

                    InformacionAreasComunesContent(
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
fun InformacionAreasComunesContent(list: List<AreasComunesItem>, modifier: Modifier = Modifier) {

    val areasComunes = list

    /*
    // Lista de datos para las áreas comunes
    val areasComunes = listOf(
        AreaComun("Sala de estar", 50),
        AreaComun("Piscina", 200),
        AreaComun("Terraza", 20),
        // Agrega más áreas comunes según sea necesario
    )
    */

    LazyColumn(
        modifier = modifier
            .background(Color.Gray) // El color de fondo de la pantalla
            .padding(16.dp)
    ) {
        items(areasComunes) { area ->
            InformacionAreaComunCard(areasComunes)
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

@Composable
fun CircleCloseButton(onClick: () -> Unit) {
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


@Composable
fun InformacionAreaComunCard(list: List<AreasComunesItem>) {
    val popupWidth = 250.dp
    Card(
        modifier = Modifier
            .width(popupWidth)
            .padding(4.dp)
    ) {
        // Card para el nombre del área
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
                .padding(8.dp)
        ) {
            Column(
                modifier = Modifier
                    .padding(16.dp)
            ) {
                list.forEach { areaItem ->
                    // Bloque para cada área común
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(Color.White)
                            .padding(8.dp)
                    ) {
                        Column(
                            modifier = Modifier
                                .padding(16.dp)
                        ) {
                            Text(
                                text = "Descripción: ${areaItem.descripcion}",
                                style = MaterialTheme.typography.titleMedium.copy(
                                    fontWeight = FontWeight.Bold
                                )
                            )
                            Text(
                                text = "Área: ${areaItem.area}",
                                style = MaterialTheme.typography.titleMedium.copy(
                                    fontWeight = FontWeight.Bold
                                )
                            )
                        }
                    }

                    // Espacio entre tarjetas
                    Spacer(modifier = Modifier.height(16.dp))
                }
            }
        }
    }
}


//data class AreaComun(val nombre: String, val areaM2: Int)
/*
@Preview
@Composable
fun InformacionAreasComunesPreview() {
    Avance_ProyectoTheme {
        InformacionAreasComunesScreen(
            idPredio = "123",
            onDimiss = {}
        )
    }
}
*/
