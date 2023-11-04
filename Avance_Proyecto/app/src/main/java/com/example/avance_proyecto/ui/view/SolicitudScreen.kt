package com.example.avance_proyecto.ui.view

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.avance_proyecto.R
import com.example.avance_proyecto.data.UsuarioDataSource
import com.example.avance_proyecto.data.uistate.UsuarioUiState
import com.example.avance_proyecto.navigation.AppScreen
import com.example.avance_proyecto.ui.theme.Avance_ProyectoTheme


@Composable
fun SolicitudScreen1(navController: NavController){
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


@Composable
fun SolicitudScreen(navController: NavController) {

    val options = listOf("Opcion 1", "Opcion 2", "Opcion 3") // Lista de opciones para el menú desplegable
    var selectedOption by remember { mutableStateOf(options.first()) }
    var isMenuExpanded by remember { mutableStateOf(false) }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(onBackClick = { navController.popBackStack() })
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

        val heroes = UsuarioDataSource.usuarios
        HeroesList(navController = navController, heroes = heroes, contentPadding = it)

    }
}

@Composable
fun TopAppBar(onBackClick: () -> Unit, modifier: Modifier = Modifier) {
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

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun HeroesList(
    navController: NavController,
    heroes: List<UsuarioUiState>,
    contentPadding: PaddingValues = PaddingValues(0.dp),
    modifier: Modifier = Modifier
) {
    val visibleState = remember {
        MutableTransitionState(false).apply {
            // Start the animation immediately.
            targetState = true
        }
    }

    // Fade in entry animation for the entire list
    AnimatedVisibility(
        visibleState = visibleState,
        enter = fadeIn(
            animationSpec = spring(dampingRatio = Spring.DampingRatioLowBouncy)
        ),
        exit = fadeOut(),
        modifier = modifier
    ) {
        LazyColumn(contentPadding = contentPadding) {
            itemsIndexed(heroes) { index, hero ->
                HeroListItem(
                    hero = hero,
                    modifier = Modifier
                        .padding(horizontal = 16.dp, vertical = 8.dp)
                        .clickable { navController.navigate(route = AppScreen.informationScreen.route) } // Llamar a la función que se necesite cuando se hace clic al elemento
                        // Animate each list item to slide in vertically
                        .animateEnterExit(
                            enter = slideInVertically(
                                animationSpec = spring(
                                    stiffness = Spring.StiffnessVeryLow,
                                    dampingRatio = Spring.DampingRatioLowBouncy
                                ),
                                initialOffsetY = { it * (index + 1) } // staggered entrance
                            )
                        )
                )
            }
        }
    }
}

@Composable
fun HeroListItem(
    hero: UsuarioUiState,
    modifier: Modifier = Modifier
) {
    Card(
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        modifier = modifier,
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .sizeIn(minHeight = 72.dp)
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = stringResource(hero.nombrecompleto),
                    style = MaterialTheme.typography.displaySmall.copy(color = Color.Cyan),
                )
                Text(
                    text = stringResource(hero.puesto),
                    style = MaterialTheme.typography.bodyLarge
                )
                Text(
                    text = stringResource(hero.predio)+" - "+stringResource(hero.ubicacion),
                    style = MaterialTheme.typography.bodyLarge
                )
                Text(
                    text = stringResource(hero.tiempo),
                    style = MaterialTheme.typography.bodyLarge.copy(color = Color.Red),
                )
            }
        }
    }
}

@Preview("Light Theme")
/*@Preview("Dark Theme", uiMode = Configuration.UI_MODE_NIGHT_YES)*/
@Composable
fun HeroPreview() {
    val hero = UsuarioUiState(
        R.string.nombrecompleto1,
        R.string.puesto1,
        R.string.predio1,
        R.string.ubicacion1,
        R.string.tiempo1
    )
    Avance_ProyectoTheme {
        HeroListItem(hero = hero)
    }
}

@Preview("Heroes List")
@Composable
fun HeroesPreview() {
    Avance_ProyectoTheme(darkTheme = false) {
        Surface (
            color = MaterialTheme.colorScheme.background
        ) {
            //HeroesList(heroes = UsuarioDataSource.usuarios)
        }
    }
}