package com.example.solicitudes_condosa

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.solicitudes_condosa.model.UsuariosRepository
import com.example.solicitudes_condosa.ui.theme.UsuarioTheme
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            UsuarioTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    UsuariosApp()
                }
            }
        }
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun UsuariosApp() {

        val options = listOf("Opcion 1", "Opcion 2", "Opcion 3") // Lista de opciones para el menú desplegable
        var selectedOption by remember { mutableStateOf(options.first()) }
        var isMenuExpanded by remember { mutableStateOf(false) }

        Scaffold(
            modifier = Modifier.fillMaxSize(),
            topBar = {
                TopAppBar(onBackClick = { /* Manejar el clic en el botón de flecha "back" aquí */ })
            }

        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { isMenuExpanded = true }
                    .padding(16.dp)
            ) {
                Text("Selecciona una opción: $selectedOption")
                /*En trabajo, todavia no funciona, se superpone con la lista de usuarios*/
                DropdownMenu(
                    expanded = isMenuExpanded,
                    onDismissRequest = { isMenuExpanded = false }
                ) {
                    options.forEach { option ->
                        DropdownMenuItem(
                            text = { Text(text = option) },
                            onClick = {
                                selectedOption = option
                                isMenuExpanded = false
                            }
                        )
                    }
                }
            }

            /*// Filtrar la lista de héroes según la opción seleccionada en el menú desplegable
            val filteredHeroes = when (selectedOption) {
                "Opción 1" -> UsuariosRepository.usuarios
                /*"Opción 2" -> UsuariosRepository.usuarios.filter { /* Criterio de filtro para Opción 2 */ }
                "Opción 3" -> UsuariosRepository.usuarios.filter { /* Criterio de filtro para Opción 3 */ }*/
                else -> emptyList() // Si ninguna opción coincide, mostrar una lista vacía
            }*/

            val heroes = UsuariosRepository.usuarios
            HeroesList(heroes = heroes, contentPadding = it)

        }
    }

    /**
     * Composable that displays a Top Bar with an icon and text.
     *
     * @param modifier modifiers to set to this composable
     */
    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun TopAppBar(modifier: Modifier = Modifier, onBackClick: () -> Unit) {
        CenterAlignedTopAppBar(
            title = {
                Text(
                    text = stringResource(R.string.frame_name),
                    style = MaterialTheme.typography.displayLarge,
                )
            },
            navigationIcon = {
                IconButton(
                    onClick = onBackClick
                ) {
                    Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Back")
                }
            },
            modifier = modifier
        )
    }



    @Preview(showBackground = true)
    @Composable
    fun SuperHeroesPreview() {
        UsuarioTheme {
            UsuariosApp()
        }
    }
}