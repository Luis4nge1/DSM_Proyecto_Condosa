package com.example.avance_proyecto.ui.view

import android.annotation.SuppressLint
import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
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
import androidx.compose.ui.window.DialogProperties
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.avance_proyecto.data.TrackingDefaultDataSource
import com.example.avance_proyecto.data.model.InformacionSolicitudItem
import com.example.avance_proyecto.data.model.SolicitudEstadoSolDTO
import com.example.avance_proyecto.ui.viewmodel.InformationViewModel
import com.example.avance_proyecto.ui.theme.ButtonColorDefault
import com.example.avance_proyecto.ui.theme.ButtonColorRed
import com.example.avance_proyecto.ui.theme.TextWhite
import com.example.avance_proyecto.ui.theme.backgroundPrincipal
import com.example.avance_proyecto.ui.viewmodel.InformacionSolicitudViewModel
import com.example.avance_proyecto.ui.viewmodel.InformacionPredioViewModel
import com.example.avance_proyecto.ui.viewmodel.SolicitudEstSolViewModel
import java.sql.Date
import java.time.LocalDate
import java.time.LocalDateTime


@Composable
fun InformationScreen(
    navController: NavController,
    body: String,
    body1: String
){

    val informacionSolicitudViewModel: InformacionSolicitudViewModel = viewModel()

    val solicitudEstSolViewModel: SolicitudEstSolViewModel = viewModel()


    val isLoading by informacionSolicitudViewModel.isLoading.collectAsState()

    val idSolicitud: Int = body.toInt()
    val idEstado: Int = body1.toInt()

    val insertarDataDTO : SolicitudEstadoSolDTO =SolicitudEstadoSolDTO(LocalDateTime.now(),idSolicitud, idEstado, "S")

    informacionSolicitudViewModel.getInformacionSolicitud(body) // Filtrar los datos según la id

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
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            items(1) {
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
                }else{
                    val listInformacionSolicitante by informacionSolicitudViewModel.idSolcitudResult.collectAsState()

                    TrackingDefaultDataSource.setListInformacionSolicitante(listInformacionSolicitante)

                    val tracking = TrackingDefaultDataSource.mostrarInformacionSolicitud()
                    tracking.forEach { it->
                        CardInformation(label = stringResource(it.labelData) , data = it.valueData)
                    }
                    ButtonSectionDetails(listInformacionSolicitante,listOf("Predio", "Solicitante", "Áreas Comunes"), "Ver detalles en:")

                    ButtonSectionOption(listOf("Cotizar", "Observar", "Anular"),
                        "Seleccionar opción:",
                        body1,
                        solicitudEstSolViewModel,
                        insertarDataDTO
                    )
                }

            }
        }
    }

}

@Composable
fun TrackingAppBarInformation(
    navigateUp: () -> Unit
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
fun CardInformation(label: String, data: String){
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
fun ButtonSectionDetails(
    list: List<InformacionSolicitudItem>,
    botones: List<String>, title: String
) {
    val context = LocalContext.current
    //val gameUiState by popUpViewModel.uiState.collectAsState()

    var selectedButtonIndex by remember {
        mutableStateOf(0)
    }

    var showDialog by remember {
        mutableStateOf(false)
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
                        showToastInformation(context, botones[it])
                        selectedButtonIndex = it
                        showDialog = true
                    },
                    colors = ButtonDefaults.buttonColors(
                        ButtonColorDefault,
                        Color.White
                    ),
                    shape = MaterialTheme.shapes.small
                ) {
                    Text(text = botones[it], color = TextWhite)
                }
            }
        }
    }

    if(showDialog && selectedButtonIndex == 0){
        PredioContent(
            list.firstOrNull()?.id_predio?: "",
            onDimiss = { showDialog = false }
        )
    }
    if(showDialog && selectedButtonIndex == 1){
        SolicitanteContent(
            list.firstOrNull()?.id_solicitante?:"",
            onDimiss = { showDialog = false }
        )
    }

    if(showDialog && selectedButtonIndex == 2){
        InformacionAreasComunesScreen(
            list.firstOrNull()?.id_predio?:"",
            onDimiss = { showDialog = false }
        )
    }


}

@SuppressLint("UnrememberedMutableState")
@Composable
fun ButtonSectionOption(
    botones: List<String>, title: String,
    body1: String,
    solicitudEstSolViewModel: SolicitudEstSolViewModel,
    insertarDataDTO : SolicitudEstadoSolDTO
) {


    val context = LocalContext.current
    //val gameUiState by popUpViewModel.uiState.collectAsState()
    var activeButton by mutableStateOf(true)

    var selectedButtonIndex by remember {
        mutableStateOf(0)
    }

    var showDialog by remember {
        mutableStateOf(false)
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
                if(body1 == "1"){
                    activeButton = false
                }
                activeButton = !(body1 == "3" && it == 2)
                activeButton = !(body1 == "2" && it == 1)
                Button(
                    enabled = activeButton ,
                    onClick = {
                        showToastInformation(context, botones[it])
                        selectedButtonIndex = it
                        showDialog = true
                    },
                    colors = ButtonDefaults.buttonColors(
                        if (botones[it] == "Anular") {
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

    if(showDialog && selectedButtonIndex == 2){
        AnulacionScreen(
            onDimiss = { showDialog = false },
            onPositiveButtonClicked = { showDialog = false },
            onNegativeButtonClicked = { showDialog = false },
            properties = DialogProperties(
                dismissOnBackPress = false,
                dismissOnClickOutside = false
            ),
            solicitudEstSolViewModel,
            insertarDataDTO
        )
    }
}

fun showToastInformation(context: Context, message: String) {
    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
}

@Preview
@Composable
fun InformationScreenPreview() {
    val navController = rememberNavController() // Importa rememberNavController
    InformationScreen(navController = navController, body = "123", body1 = "0") // Agrega un valor para el parámetro 'body'
}