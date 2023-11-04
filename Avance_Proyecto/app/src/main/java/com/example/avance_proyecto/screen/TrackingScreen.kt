package com.example.avance_proyecto.screen

import TrackingEstadoSolicitud.getTrackingData
import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.rememberNavController
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import co.yml.charts.axis.AxisData
import co.yml.charts.axis.DataCategoryOptions
import co.yml.charts.common.model.PlotType
import co.yml.charts.common.model.Point
import co.yml.charts.common.utils.DataUtils
import co.yml.charts.ui.barchart.BarChart
import co.yml.charts.ui.barchart.models.BarChartData
import co.yml.charts.ui.barchart.models.BarChartType
import co.yml.charts.ui.linechart.LineChart
import co.yml.charts.ui.linechart.model.GridLines
import co.yml.charts.ui.linechart.model.IntersectionPoint
import co.yml.charts.ui.linechart.model.Line
import co.yml.charts.ui.linechart.model.LineChartData
import co.yml.charts.ui.linechart.model.LinePlotData
import co.yml.charts.ui.linechart.model.LineStyle
import co.yml.charts.ui.linechart.model.LineType
import co.yml.charts.ui.linechart.model.SelectionHighlightPoint
import co.yml.charts.ui.linechart.model.SelectionHighlightPopUp
import co.yml.charts.ui.linechart.model.ShadowUnderLine
import co.yml.charts.ui.piechart.charts.DonutPieChart
import co.yml.charts.ui.piechart.models.PieChartConfig
import co.yml.charts.ui.piechart.models.PieChartData
import com.example.avance_proyecto.R
import com.example.avance_proyecto.data.APIService
import com.example.avance_proyecto.data.TrackingDataSource
import com.example.avance_proyecto.model.SearchState
import com.example.avance_proyecto.model.TrackingCard
import com.example.avance_proyecto.navigation.AppScreen
import com.example.avance_proyecto.ui.OrderViewModel
import com.example.avance_proyecto.ui.SearchViewModel
import com.example.avance_proyecto.ui.standardQuadFromTo
import com.example.avance_proyecto.ui.theme.Avance_ProyectoTheme
import com.example.avance_proyecto.ui.theme.ButtonBlue
import com.example.avance_proyecto.ui.theme.DarkerButtonBlue
import com.example.avance_proyecto.ui.theme.TextWhite
import com.example.avance_proyecto.ui.theme.backgroundPrincipal


@Composable
fun TrackingAppBar(
    navigateUp: () -> Unit,
    searchWidgetState: SearchState,
    searchTextState: String,
    onTextChange: (String) -> Unit,
    onCloseClicked: () -> Unit,
    onSearchClicked: (String) -> Unit,
    onSearchTriggered: () -> Unit
) {
    when (searchWidgetState) {
        SearchState.CLOSED -> {
            DefaultTrackingAppBar(
                navigateUp,
                onSearchClicked = onSearchTriggered
            )
        }
        SearchState.OPENED -> {
            SearchTrackingAppBar(
                text = searchTextState,
                onTextChange = onTextChange,
                onCloseClicked = onCloseClicked,
                onSearchClicked = onSearchClicked
            )
        }
    }
}

@Composable
fun DefaultTrackingAppBar(
    navigateUp: () -> Unit,
    onSearchClicked: () -> Unit,
    modifier: Modifier = Modifier
) {
    TopAppBar(
        modifier = Modifier.background(backgroundPrincipal),
        title = {
            Row(
                modifier = Modifier.fillMaxSize(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = "Hola, John Doe", color = Color.White)
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconButton(
                        onClick = { onSearchClicked() }
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Search,
                            contentDescription = "Search Icon",
                            tint = Color.White
                        )
                    }
                    Image(
                        modifier = Modifier
                            .size(64.dp)
                            .padding(8.dp),
                        painter = painterResource(R.drawable.user),
                        contentDescription = null
                    )
                }

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
fun SearchTrackingAppBar(
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
                }
            },
            trailingIcon = {
                IconButton(
                    onClick = {
                        if (text.isNotEmpty()) {
                            onTextChange("")
                        } else {
                            onCloseClicked()
                        }
                    }
                ) {
                    Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = "Close Icon",
                        tint = Color.Black.copy(alpha = 0.5f)
                    )
                }
            },
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
fun TrackingScreen(
    navController: NavController,
    viewModel: OrderViewModel = viewModel(),
    mainViewModel: SearchViewModel = SearchViewModel(),
    modifier: Modifier = Modifier
) {
    //val backStackEntry by navController.currentBackStackEntryAsState()
    // Get the name of the current screen
    /*val currentScreen = CupcakeScreen.valueOf(
        backStackEntry?.destination?.route ?: CupcakeScreen.Start.name
    )*/

    val searchWidgetState by mainViewModel.searchWidgetState
    val searchTextState by mainViewModel.searchTextState

    // Obtener datos de la API
    LaunchedEffect(Unit) {
        val trackingData = getTrackingData()
        if (trackingData != null) {
            mainViewModel.updateTrackingData(trackingData)
        }
    }

    Scaffold(
        topBar = {
            TrackingAppBar(
                navigateUp = { navController.navigateUp() },
                searchWidgetState = searchWidgetState,
                searchTextState = searchTextState,
                onTextChange = {
                    mainViewModel.updateSearchTextState(newValue = it)
                },
                onCloseClicked = {
                    mainViewModel.updateSearchWidgetState(newValue = SearchState.CLOSED)
                },
                onSearchClicked = {
                    Log.d("Searched Text", it)
                },
                onSearchTriggered = {
                    mainViewModel.updateSearchWidgetState(newValue = SearchState.OPENED)
                }
            )
        }
    ) { innerPadding ->
        val uiState by viewModel.uiState.collectAsState()
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .background(backgroundPrincipal)
                .fillMaxSize()
        ) {
            ButtonSectionTracking(listOf("Distribución","Tendencia","Evolución"))

            val tracking = TrackingDataSource.itemCardTracking //Estados de solicitudes (Pendiente, Cotizado, Observado y Anulado)
            TopicCuadricula(
                topicList = tracking,
                navController = navController
            )
        }
    }
}


@Composable
fun ButtonSectionTracking(
    botones: List<String>
) {
    val context = LocalContext.current

    var selectedChipIndex by remember {
        mutableStateOf(0)
    }

    Column{

        LazyRow {
            items(botones.size) {
                Button(
                    onClick = {
                        showToastTracking(context, "Hola $it")
                        selectedChipIndex = it
                    },
                    modifier = Modifier
                        .padding(start = 15.dp, top = 15.dp, bottom = 15.dp),
                    colors = ButtonDefaults.buttonColors(
                        if (selectedChipIndex == it) {
                            ButtonBlue
                        } else {
                            DarkerButtonBlue
                        },
                        Color.White
                    ),
                    shape = MaterialTheme.shapes.small
                ) {
                    Text(text = botones[it], color = TextWhite)
                }

            }
        }
        if(selectedChipIndex==0){
            PieChartScreen()
        }else{
            if(selectedChipIndex==1){
                BarChartScreen()
            }else{
                LineChartScreen()
            }
        }
    }
}

@Preview
@Composable
fun TrackingScreenPreview() {
    Avance_ProyectoTheme(darkTheme = false) {
        val navController = rememberNavController()
        Surface {
            TrackingScreen(navController)
        }
    }
}


@Composable
fun TopicCard(topic: TrackingCard, navController: NavController, modifier: Modifier = Modifier) {

    BoxWithConstraints(
        modifier = Modifier
            .padding(7.5.dp)
            .aspectRatio(1f)
            .clip(RoundedCornerShape(10.dp))
            .background(topic.darkColor)
    ) {

        val width = constraints.maxWidth
        val height = constraints.maxHeight

        // Medium colored path
        val mediumColoredPoint1 = Offset(0f, height * 0.3f)
        val mediumColoredPoint2 = Offset(width * 0.1f, height * 0.35f)
        val mediumColoredPoint3 = Offset(width * 0.4f, height * 0.05f)
        val mediumColoredPoint4 = Offset(width * 0.75f, height * 0.7f)
        val mediumColoredPoint5 = Offset(width * 1.4f, -height.toFloat())

        val mediumColoredPath = Path().apply {
            moveTo(mediumColoredPoint1.x, mediumColoredPoint1.y)
            standardQuadFromTo(mediumColoredPoint1, mediumColoredPoint2)
            standardQuadFromTo(mediumColoredPoint2, mediumColoredPoint3)
            standardQuadFromTo(mediumColoredPoint3, mediumColoredPoint4)
            standardQuadFromTo(mediumColoredPoint4, mediumColoredPoint5)
            lineTo(width.toFloat() + 100f, height.toFloat() + 100f)
            lineTo(-100f, height.toFloat() + 100f)
            close()
        }

        // Light colored path
        val lightPoint1 = Offset(0f, height * 0.35f)
        val lightPoint2 = Offset(width * 0.1f, height * 0.4f)
        val lightPoint3 = Offset(width * 0.3f, height * 0.35f)
        val lightPoint4 = Offset(width * 0.65f, height.toFloat())
        val lightPoint5 = Offset(width * 1.4f, -height.toFloat() / 3f)

        val lightColoredPath = Path().apply {
            moveTo(lightPoint1.x, lightPoint1.y)
            standardQuadFromTo(lightPoint1, lightPoint2)
            standardQuadFromTo(lightPoint2, lightPoint3)
            standardQuadFromTo(lightPoint3, lightPoint4)
            standardQuadFromTo(lightPoint4, lightPoint5)
            lineTo(width.toFloat() + 100f, height.toFloat() + 100f)
            lineTo(-100f, height.toFloat() + 100f)
            close()
        }
        Canvas(
            modifier = Modifier
                .fillMaxSize()
        ) {
            drawPath(
                path = mediumColoredPath,
                color = topic.mediumColor
            )
            drawPath(
                path = lightColoredPath,
                color = topic.lightColor
            )
        }

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(15.dp)
        ) {
            Text(
                text = stringResource(topic.name)+"\n"+ topic.cantidad.toString(),
                style = MaterialTheme.typography.bodyLarge,
                lineHeight = 26.sp,
                modifier = Modifier.align(Alignment.TopStart)
            )
            Image(
                painter = painterResource(id = topic.imageRes),
                contentDescription = stringResource(topic.name),
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .size(width = 40.dp, height = 40.dp),
                contentScale = ContentScale.FillWidth
            )
            Text(
                text = "Ver más",
                color = TextWhite,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .clickable {
                        navController.navigate(route = AppScreen.solicitudScreen.route)
                    }
                    .align(Alignment.BottomEnd)
                    .clip(RoundedCornerShape(10.dp))
                    .background(ButtonBlue)
                    .padding(vertical = 6.dp, horizontal = 15.dp)
            )
        }

    }

}

@Composable
fun TopicCuadricula(topicList: List<TrackingCard>, navController: NavController, modifier: Modifier = Modifier){
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier.padding(8.dp)
    ) {

        items(topicList) { topic ->
            TopicCard(topic, navController)
        }
    }
}

@Composable
fun LineChartScreen(){

    val steps=5
    val pointsData: List<Point> =
        listOf(
            Point(0f, 40f),
            Point(1f, 90f),
            Point(2f, 0f),
            Point(3f, 60f),
            Point(4f, 10f)
        )

    val xAxisData = AxisData.Builder()
        .axisStepSize(100.dp)
        .backgroundColor(Color.Transparent)
        .steps(pointsData.size - 1)
        .labelData { i -> i.toString() }
        .labelAndAxisLinePadding(15.dp)
        .build()

    val yAxisData = AxisData.Builder()
        .steps(steps)
        .backgroundColor(Color.Transparent)
        .labelAndAxisLinePadding(20.dp)
        .labelData { i ->
            val yScale = 100 / steps
            (i * yScale).toString()
        }.build()

    val lineChartData = LineChartData(
        linePlotData = LinePlotData(
            lines = listOf(
                Line(
                    dataPoints = pointsData,
                    LineStyle(
                        color = MaterialTheme.colorScheme.tertiary,
                        lineType = LineType.SmoothCurve(false)
                    ),
                    IntersectionPoint(
                        color = MaterialTheme.colorScheme.tertiary
                    ),
                    SelectionHighlightPoint(
                        color = MaterialTheme.colorScheme.primary
                    ),
                    ShadowUnderLine(
                        alpha = 0.5f,
                        brush = Brush.verticalGradient(
                            colors = listOf(
                                MaterialTheme.colorScheme.inversePrimary,
                                Color.Transparent
                            )
                        )
                    ),
                    SelectionHighlightPopUp()
                )
            ),
        ),
        xAxisData = xAxisData,
        yAxisData = yAxisData,
        gridLines = GridLines(
            color = MaterialTheme.colorScheme.outlineVariant
        ),
        backgroundColor = MaterialTheme.colorScheme.surface
    )

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .clip(MaterialTheme.shapes.medium)
            .background(Color.Black)
    ){
        LineChart(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp),
            lineChartData = lineChartData
        )
    }
}

@Composable
fun BarChartScreen(){
    val stepSize = 5
    val barsData = DataUtils.getBarChartData(
        listSize = 8,
        maxRange = 8,
        barChartType = BarChartType.VERTICAL,
        dataCategoryOptions = DataCategoryOptions()
    )

    val xAxisData = AxisData.Builder()
        .axisStepSize(100.dp)
        .steps(barsData.size - 1)
        .bottomPadding(40.dp)
        .axisLabelAngle(20f)
        .labelData { index -> barsData[index].label }
        .axisLineColor(MaterialTheme.colorScheme.tertiary)
        .axisLabelColor(MaterialTheme.colorScheme.tertiary)
        .build()

    val yAxisData = AxisData.Builder()
        .steps(stepSize)
        .labelAndAxisLinePadding(20.dp)
        .axisOffset(20.dp)
        .labelData { index -> (index * (100 / stepSize)).toString() }
        .axisLineColor(MaterialTheme.colorScheme.tertiary)
        .axisLabelColor(MaterialTheme.colorScheme.tertiary)
        .build()

    val barChartData = BarChartData(
        chartData = barsData,
        xAxisData = xAxisData,
        yAxisData = yAxisData,
        backgroundColor = MaterialTheme.colorScheme.surface
    )

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .clip(MaterialTheme.shapes.medium)
            .background(Color.Black)
    ) {
        BarChart(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp),
            barChartData = barChartData
        )
    }
}

@Composable
fun PieChartScreen(){
    val donutChartData = PieChartData(
        slices = listOf(
            PieChartData.Slice("HP", 20f, Color(0xFF5F0A87)),
            PieChartData.Slice("Dell", 30f, Color(0xFF20BF55)),
            PieChartData.Slice("Lenovo", 40f,  Color(0xFFEC9F05)),
            PieChartData.Slice("Asus", 10f, Color(0xFFF53844))
        ),
        plotType = PlotType.Pie
    )

    val donutChartConfig = PieChartConfig(
        strokeWidth = 120f,
        activeSliceAlpha = .9f,
        isAnimationEnable = true,
        showSliceLabels = false,
        animationDuration = 600,
        labelVisible = true,
        isSumVisible = true
    )

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .clip(MaterialTheme.shapes.medium)
            .background(Color.Black),
        colors = CardDefaults.cardColors(Color.White)
    ) {
        DonutPieChart(
            modifier = Modifier
                .width(200.dp)
                .height(200.dp)
                .align(Alignment.CenterHorizontally),
            donutChartData,
            donutChartConfig
        )
    }

}

fun showToastTracking(context: Context, message: String) {
    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
}

