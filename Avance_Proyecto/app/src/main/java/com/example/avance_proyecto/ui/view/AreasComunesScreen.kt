package com.example.avance_proyecto.screen

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.platform.LocalTextInputService
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.avance_proyecto.R
import com.example.avance_proyecto.ui.theme.Avance_ProyectoTheme
import com.example.avance_proyecto.ui.theme.BackgroundComunesCard
import com.example.avance_proyecto.ui.theme.ButtonColorDefault
import com.example.avance_proyecto.ui.theme.DarkerButtonBlue
import com.example.avance_proyecto.ui.theme.backgroundPrincipal

class InformacionAreasComunesActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Avance_ProyectoTheme {
                InformacionAreasComunesScreen()
            }
        }
    }
}

@Composable
fun InformacionAreasComunesScreen() {
    val popupWidth = 300.dp
    val popupHeight = 400.dp

    Column(
        modifier = Modifier
            .width(popupWidth)
            .height(popupHeight)
            .background(backgroundPrincipal) // Cambia el color de fondo de la pantalla
            .padding(16.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp), // Altura del área del título y el botón
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(
                onClick = {
                    // Acción al hacer clic en el botón de cerrar
                },
                modifier = Modifier.size(48.dp)
            ) {
                Icon(imageVector = Icons.Default.Close, contentDescription = "Cerrar", tint = Color.White)
            }
            Text(
                text = "Información de áreas comunes",
                color = Color.White,
                style = MaterialTheme.typography.titleMedium.copy(
                    fontWeight = FontWeight.Bold
                )
            )
        }

        InformacionAreasComunesContent()
    }
}

@Composable
fun InformacionAreasComunesContent(modifier: Modifier = Modifier) {
    // Lista de datos para las áreas comunes
    val areasComunes = listOf(
        AreaComun("Sala de estar", 50),
        AreaComun("Piscina", 200),
        // Agrega más áreas comunes según sea necesario
    )

    LazyColumn(
        modifier = modifier
            .background(Color.Gray) // Cambia el color de fondo de la pantalla
            .padding(16.dp)
    ) {
        items(areasComunes) { area ->
            InformacionAreaComunCard(area)
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

@Composable
fun InformacionAreaComunCard(areaComun: AreaComun) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp) // Ajusta el tamaño de las cards según sea necesario
            .padding(8.dp)
            .background(BackgroundComunesCard) // Cambia el color de fondo de la card
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
        ) {
            Text(
                text = areaComun.nombre,
                style = MaterialTheme.typography.titleMedium.copy(
                    fontWeight = FontWeight.Bold
                )
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Área en M2: ${areaComun.areaM2}",
                style = MaterialTheme.typography.titleMedium
            )
        }
    }
}

data class AreaComun(val nombre: String, val areaM2: Int)

@Preview
@Composable
fun InformacionAreasComunesPreview() {
    Avance_ProyectoTheme {
        InformacionAreasComunesScreen()
    }
}
