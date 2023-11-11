// SolicitanteScreen.kt
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.example.avance_proyecto.ui.theme.Avance_ProyectoTheme
import com.example.avance_proyecto.ui.theme.backgroundPrincipal

@Composable
fun SolicitanteContent(
    onDimiss: ()->Unit,
    properties: DialogProperties=DialogProperties()
) {
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
                            text = "Información del Solicitante",
                            color = Color.White,
                            style = MaterialTheme.typography.titleMedium.copy(
                                fontWeight = FontWeight.Bold
                            )
                        )
                    },
                    navigationIcon = {
                        CircleCButton(onDimiss)
                    },
                    modifier = Modifier.background(backgroundPrincipal),
                    colors = TopAppBarDefaults.mediumTopAppBarColors(
                        containerColor = backgroundPrincipal
                    ),
                )

                SolicitanteDetailsContent(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp)
                )
            }
        }
    }

}

@Composable
fun SolicitanteDetailsContent(modifier: Modifier = Modifier) {
    // Lista de datos para los detalles del solicitante
    val solicitantes = listOf(
        Solicitante("Juan", "Pérez", "Gómez", "DNI", "12345678", "Lima", "987654321", "Calle 123", "juan@example.com"),
        Solicitante("Ana", "García", "López", "Pasaporte", "87654321", "Arequipa", "987123456", "Av. Principal", "ana@example.com"),
        // Agrega más solicitantes según sea necesario
    )

    LazyColumn(
        modifier = modifier
            .background(Color.Gray)
            .padding(16.dp)
    ) {
        items(solicitantes) { solicitante ->
            SolicitanteCard(solicitante)
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

@Composable
fun SolicitanteCard(solicitante: Solicitante) {
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
                    text = "Nombre: ${solicitante.nombre} ",
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
                    text = "Apellido paterno: ${solicitante.apellidoPaterno}",
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
                    text = "Apellido materno: ${solicitante.apellidoMaterno}",
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
                    text = "Tipo de Documento: ${solicitante.tipoDocumento}",
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
                    text = "N° de Documento: ${solicitante.numeroDocumento}",
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
                    text = "Ubicacion: ${solicitante.ubicacion}",
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
                    text = "N° de contacto: ${solicitante.numeroContacto}",
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
                    text = "Dirección: ${solicitante.direccion}",
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
                    text = "Correo: ${solicitante.correo}",
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontWeight = FontWeight.Bold
                    )
                )
            }
        }

    }
}


data class Solicitante(
    val nombre: String,
    val apellidoPaterno: String,
    val apellidoMaterno: String,
    val tipoDocumento: String,
    val numeroDocumento: String,
    val ubicacion: String,
    val numeroContacto: String,
    val direccion: String,
    val correo: String
)

@Composable
fun CircleCButton(onClick: () -> Unit) {
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

@Preview
@Composable
fun SolicitantePreview() {
    Avance_ProyectoTheme {
        SolicitanteContent(onDimiss = {})
    }
}