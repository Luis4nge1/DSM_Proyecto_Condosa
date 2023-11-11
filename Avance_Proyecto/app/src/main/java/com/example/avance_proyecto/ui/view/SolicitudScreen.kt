package com.example.avance_proyecto.ui.view

import android.content.res.Configuration
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.surfaceColorAtElevation
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.avance_proyecto.R
import com.example.avance_proyecto.data.UsuarioDataSource
import com.example.avance_proyecto.data.model.SolicitudesEstadoItem
import com.example.avance_proyecto.data.uistate.SearchUiState
import com.example.avance_proyecto.data.uistate.UsuarioUiState
import com.example.avance_proyecto.navigation.AppScreen
import com.example.avance_proyecto.ui.theme.Avance_ProyectoTheme
import com.example.avance_proyecto.ui.theme.InformationCardContainer
import com.example.avance_proyecto.ui.theme.backgroundCard
import com.example.avance_proyecto.ui.theme.backgroundPrincipal
import com.example.avance_proyecto.ui.viewmodel.SolicitudViewModel
import com.example.avance_proyecto.ui.viewmodel.SolicitudesEstadoViewModel
import kotlinx.coroutines.launch
import java.lang.NumberFormatException
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun SolicitudScreen(navController: NavController, body: String) {

    val solicitudViewModel: SolicitudViewModel = viewModel()
    val solicitudesEstadoViewModel: SolicitudesEstadoViewModel = viewModel()

    val isLoading by solicitudesEstadoViewModel.isLoading.collectAsState()

    val listadoSolicitudesEstadoPendiente by solicitudesEstadoViewModel.solicitudesEstadoPendienteResult.collectAsState()
    val listadoSolicitudesEstadoCotizado by solicitudesEstadoViewModel.solicitudesEstadoCotizadoResult.collectAsState()
    val listadoSolicitudesEstadoObservado by solicitudesEstadoViewModel.solicitudesEstadoObservadoResult.collectAsState()
    val listadoSolicitudesEstadoAnulado by solicitudesEstadoViewModel.solicitudesEstadoAnuladoResult.collectAsState()

    val anulado by solicitudesEstadoViewModel.filtradoAnulado.collectAsState()
    val pendiente by solicitudesEstadoViewModel.filtradoPendiente.collectAsState()
    val cotizado by solicitudesEstadoViewModel.filtradoCotizado.collectAsState()
    val observado by solicitudesEstadoViewModel.filtradoObservado.collectAsState()

    val searchText by solicitudViewModel.searchText.collectAsState()
    val isSearching by solicitudViewModel.isSearching.collectAsState()
    val searchWidgetState by solicitudViewModel.searchWidgetState.collectAsState()

    val collectionTabs = listOf("Pendiente", "Cotizado", "Observado", "Anulado") // Lista de opciones para el menú desplegable
    //var selectedOption by remember { mutableStateOf(options.first()) }
    //var isMenuExpanded by remember { mutableStateOf(false) }

    val dataBody: Int = try {
        body.toInt()
    }catch (e: NumberFormatException){
        0
    }
    var tabState by remember { mutableStateOf(dataBody) }
    val pagerState = rememberPagerState(dataBody)
    val scope = rememberCoroutineScope()
    LaunchedEffect(key1 = pagerState.currentPage){
        tabState = pagerState.currentPage
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            Column{
                SolcitudAppBar(
                    navigateUp = {navController.navigateUp()},
                    searchWidgetState = searchWidgetState,
                    searchTextState = searchText,
                    onTextChange = {
                        solicitudViewModel.updateSearchTextState(newValue = it)
                    },
                    onCloseClicked = {
                        solicitudViewModel.updateSearchWidgetState(newValue = SearchUiState.CLOSED)
                    },
                    onSearchClicked = {
                        println("CLICKEADO")
                    },
                    onSearchTriggered = {
                        solicitudViewModel.updateSearchWidgetState(newValue = SearchUiState.OPENED)
                    },
                    onBackClick = { navController.popBackStack() }
                )
                TabRow(
                    selectedTabIndex = minOf(collectionTabs.count(),tabState) ,
                    containerColor = MaterialTheme.colorScheme.surfaceColorAtElevation(3.dp)
                ) {
                    collectionTabs.forEachIndexed { index, tabName ->
                        Tab(
                            modifier = Modifier.padding(4.dp),
                            selected = index == pagerState.currentPage,
                            onClick = {
                                tabState = index
                                scope.launch {
                                    pagerState.animateScrollToPage(index)
                                }
                            }
                        ) {
                            Text(text = tabName)
                        }
                    }
                }
            }

        }

    ) { it ->
        println(it)
        //var dataFiltradaGeneral = emptyList<SolicitudesEstadoItem>()
        var dataPendiente = emptyList<SolicitudesEstadoItem>()
        var dataObservado = emptyList<SolicitudesEstadoItem>()
        var dataCotizado = emptyList<SolicitudesEstadoItem>()
        var dataAnulado = emptyList<SolicitudesEstadoItem>()

        when(searchWidgetState){
            SearchUiState.OPENED->{
                solicitudesEstadoViewModel.getFiltradoPendiente(searchText)
                dataPendiente = pendiente
                solicitudesEstadoViewModel.getFiltradoCotizado(searchText)
                dataCotizado = cotizado
                solicitudesEstadoViewModel.getFiltradoAnulado(searchText)
                dataAnulado = anulado
                solicitudesEstadoViewModel.getFiltradoObservado(searchText)
                dataObservado = observado
            }
            SearchUiState.CLOSED->{
                dataPendiente = listadoSolicitudesEstadoPendiente
                dataCotizado = listadoSolicitudesEstadoCotizado
                dataAnulado = listadoSolicitudesEstadoAnulado
                dataObservado = listadoSolicitudesEstadoObservado
            }
        }
        //println("FILTRANDO LOS DATOS: "+dataUserFilter)

            if(isLoading){
                Column(
                    modifier = Modifier
                        .fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ){
                    CircularProgressIndicator(
                        color = backgroundPrincipal,
                        modifier = Modifier.size(50.dp).padding(it)
                    )
                }
            }else{
                HorizontalPager(
                    pageCount = collectionTabs.count(),
                    state = pagerState,
                    userScrollEnabled = true
                ) {tabIndex ->
                    when(tabIndex){
                        0 -> {
                            SolicitudList(
                                navController = navController,
                                heroes = dataPendiente,
                                contentPadding = it
                            )
                        }
                        1 -> {
                            SolicitudList(navController = navController,
                                heroes = dataCotizado,
                                contentPadding = it
                            )
                        }
                        2 -> {
                            SolicitudList(navController = navController,
                                heroes = dataObservado,
                                contentPadding = it
                            )
                        }
                        3 -> {
                            SolicitudList(navController = navController,
                                heroes = dataAnulado,
                                contentPadding = it
                            )
                        }
                    }
                }
            }
    }
}

@Composable
fun SolcitudAppBar(
    navigateUp: () -> Unit,
    searchWidgetState: SearchUiState,
    searchTextState: String,
    onTextChange: (String) -> Unit,
    onCloseClicked: () -> Unit,
    onSearchClicked: (String) -> Unit,
    onSearchTriggered: () -> Unit,
    onBackClick: () -> Unit
) {
    when (searchWidgetState) {
        SearchUiState.CLOSED -> {
            DefaultSolicitudAppBar(
                navigateUp,
                onSearchClicked = onSearchTriggered,
                onBackClick = onBackClick
            )
        }
        SearchUiState.OPENED -> {
            SearchSolcitudAppBar(
                text = searchTextState,
                onTextChange = onTextChange,
                onCloseClicked = onCloseClicked,
                onSearchClicked = onSearchClicked
            )
        }
    }
}

@Composable
fun DefaultSolicitudAppBar(
    navigateUp: () -> Unit,
    onBackClick: () -> Unit,
    onSearchClicked: () -> Unit,
) {
    TopAppBar(
        title = {
            Row(
                modifier = Modifier.fillMaxSize(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ){
                Text(
                    text = "Solicitudes",
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )
                IconButton(
                    onClick = { onSearchClicked() }
                ) {
                    Icon(
                        imageVector = Icons.Filled.Search,
                        contentDescription = "Search Icon",
                        tint = Color.White
                    )
                }
            }

        },
        navigationIcon = {
            IconButton(
                onClick = onBackClick
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Back",
                    tint = Color.White
                )
            }
        },
        modifier = Modifier.background(backgroundPrincipal),
        colors = TopAppBarDefaults.mediumTopAppBarColors(
            containerColor = backgroundPrincipal
        )
    )
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun SolicitudList(
    navController: NavController,
    heroes: List<SolicitudesEstadoItem>,
    contentPadding: PaddingValues = PaddingValues(0.dp),
    modifier: Modifier = Modifier
) {
    val visibleState = remember {
        MutableTransitionState(false).apply {
            // Start the animation immediately.
            targetState = true
        }
    }
    Column(
        modifier = modifier
            .padding(contentPadding)
            .fillMaxSize()
    ){
        Text(
            modifier = Modifier
                .padding(horizontal = 16.dp, vertical = 8.dp).fillMaxWidth(1f),
            text = "("+heroes.count()+") resultado${if(heroes.count()>1) "s" else "" }",
            color = backgroundCard,
            style = MaterialTheme.typography.titleLarge,
            textAlign = TextAlign.Center
        )
        AnimatedVisibility(
            visibleState = visibleState,
            enter = fadeIn(
                animationSpec = spring(dampingRatio = Spring.DampingRatioLowBouncy)
            ),
            exit = fadeOut(),
            modifier = modifier
        ) {
            LazyColumn {
                itemsIndexed(heroes) { index, hero ->
                    SolicitudListItem(
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

    // Fade in entry animation for the entire list

}

@Composable
fun SearchSolcitudAppBar(
    text: String,
    onTextChange: (String) -> Unit,
    onCloseClicked: () -> Unit,
    onSearchClicked: (String) -> Unit,
){
    val context = LocalContext.current

    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp),
        color = backgroundPrincipal
    ){
        TextField(
            modifier = Modifier
                .fillMaxWidth(),
            value = text,
            onValueChange = {
                onTextChange(it)
                println(it)
            },
            placeholder = {
                Text(
                    modifier = Modifier
                        .alpha(0.5f),
                    text = "Buscar...",
                    color = Color.Black
                )
            },
            textStyle = LocalTextStyle.current,
            singleLine = true,
            leadingIcon = {
                IconButton(
                    modifier = Modifier
                        .alpha(0.5f),
                    onClick = {}
                ) {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = "Search Icon",
                        tint = Color.Black
                    )
                } },

            trailingIcon = {
                IconButton(
                    onClick = {
                        if (text.isNotEmpty()) {
                            onTextChange("")
                        } else {
                            println("GAAA")
                            onCloseClicked()
                        }
                    }
                ) {
                    Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = "Close Icon",
                        tint = Color.Black.copy(alpha = 0.5f)
                    )
                } },
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Search
            ),
            keyboardActions = KeyboardActions(
                onSearch = {
                    showToastTracking(context, "Buscando la palabra: $text")
                    onSearchClicked(text)
                }
            ),
            colors = TextFieldDefaults.colors(
                focusedTextColor = Color.Black.copy(alpha = 0.5f),
                cursorColor = Color.Black.copy(alpha = 0.5f)
            )
        )
    }
}

@Composable
fun SolicitudListItem(
    hero: SolicitudesEstadoItem,
    modifier: Modifier = Modifier
) {

    val tiempo = obtenerTiempoTranscurrido(hero.fecha)

    Card(
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        modifier = modifier,
        colors = CardDefaults.cardColors(InformationCardContainer)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .sizeIn(minHeight = 72.dp)
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = hero.nombre_solicitante,
                    color = Color.Cyan,
                    style = MaterialTheme.typography.titleMedium
                )
                Text(
                    text = hero.departamento,
                    style = MaterialTheme.typography.bodyLarge,
                    color = Color.White
                )
                Text(
                    text = hero.descripcion_predio+" - "+hero.provincia+" ("+hero.distrito+")",
                    style = MaterialTheme.typography.bodyLarge,
                    color = Color.White
                )
                Text(
                    text = tiempo,
                    style = MaterialTheme.typography.bodyLarge.copy(color = Color.Red),
                )
            }
        }
    }
}

fun obtenerTiempoTranscurrido(fecha: String): String {
    val formato = SimpleDateFormat("E, dd MMM yyyy HH:mm:ss z", Locale.ENGLISH)
    val fechaObjeto = formato.parse(fecha)
    val diferencia = Date().time-fechaObjeto.time

    val segundos = diferencia/1000
    val minutos = segundos/60
    val horas = minutos/60
    val dias = horas/24
    val semanas = dias/7
    val meses = dias/30
    val anios = meses/12

    return when{
        anios > 0 -> "Hace $anios año${if(anios>1) "s" else ""}"
        meses > 0 -> "Hace $meses mes${if(meses>1) "es" else ""}"
        semanas > 0 -> "Hace $semanas semana${if(semanas>1) "s" else ""}"
        dias > 0 -> "Hace $dias dia${if(dias>1) "s" else ""}"
        horas > 0 -> "Hace $horas hora${if(horas>1) "s" else ""}"
        minutos > 0 -> "Hace $minutos minuto${if(minutos>1) "s" else ""}"
        else -> "Hace $segundos segundo${if(segundos>1) "s" else ""}"
    }
}
/*
@Preview("Light Theme")
@Preview("Dark Theme", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun HeroPreview() {
    val hero = UsuarioUiState(
        R.string.nombrecompleto1,
        R.string.puesto1,
        R.string.predio1,
        R.string.ubicacion1,
        R.string.tiempo1,
        "Datos"
    )
    Avance_ProyectoTheme {
        //SolicitudListItem(hero = hero)
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
*/
@OptIn(ExperimentalFoundationApi::class)
@Composable
@Preview(showBackground = true)
fun SolicitudScreenPreview() {
    val navController = rememberNavController()
    val body = "3" // Puedes ajustar esto según tus necesidades

    Avance_ProyectoTheme {
        // Asegúrate de proporcionar instancias reales de tus ViewModels
        SolicitudScreen(navController = navController, body = body)
    }
}