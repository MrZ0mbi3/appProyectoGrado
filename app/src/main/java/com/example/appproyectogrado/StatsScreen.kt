package com.example.appproyectogrado

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import co.yml.charts.axis.AxisData
import co.yml.charts.common.extensions.formatToSinglePrecision
import co.yml.charts.common.model.Point
import co.yml.charts.common.utils.DataUtils
import co.yml.charts.ui.barchart.BarChart
import co.yml.charts.ui.barchart.models.BarChartData
import co.yml.charts.ui.linechart.LineChart
import co.yml.charts.ui.linechart.model.*
import com.example.appproyectogrado.viewmodel.StatsScreenViewModel
import kotlinx.coroutines.launch

@Composable
fun StatsScreen(navController: NavHostController = rememberNavController()) {
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()
    val statsScreenViewModel: StatsScreenViewModel = viewModel()
    Scaffold(modifier = Modifier
        .fillMaxSize()
        .background(color = Color.DarkGray),
        scaffoldState = scaffoldState,
        topBar = {
            TopAppBar {
                IconButton(onClick = { scope.launch { scaffoldState.drawerState.open() } }) {
                    Icon(Icons.Filled.Menu, contentDescription = "burgerBottom")
                }
                Text(text = stringResource(id = R.string.estadisticas))
            }
        }, drawerContent = {
            DrawerContent(navController)
        }) {
        Stats(statsScreenViewModel)
    }
}

@Composable
fun Stats(statsScreenViewModel: StatsScreenViewModel) {
    val steps = 5
    val pointsData: List<Point> =
        listOf(Point(0f, 40f), Point(1f, 90f), Point(2f, 0f), Point(3f, 60f), Point(4f, 10f))
    val xAxisData = AxisData.Builder()
        .axisStepSize(100.dp)
        .steps(pointsData.size - 1)
        .labelData { i -> i.toString() }
        .labelAndAxisLinePadding(15.dp)
        .build()

    val yAxisData = AxisData.Builder()
        .steps(steps)
        .labelAndAxisLinePadding(20.dp)
        .labelData { i ->
            // Add yMin to get the negative axis values to the scale
            val yMin = pointsData.minOf { it.y }
            val yMax = pointsData.maxOf { it.y }
            val yScale = (yMax - yMin) / steps
            ((i * yScale) + yMin).formatToSinglePrecision()
        }.build()

    val lineChartData = LineChartData(
        linePlotData = LinePlotData(
            lines = listOf(
                Line(
                    dataPoints = pointsData,
                    LineStyle(),
                    IntersectionPoint(),
                    SelectionHighlightPoint(),
                    ShadowUnderLine(),
                    SelectionHighlightPopUp()
                )
            ),
        ),
        xAxisData = xAxisData,
        yAxisData = yAxisData,
        gridLines = GridLines()
    )

    val barChartData = DataUtils.getBarChartData(50, 30)
    val yStepSize = 10
    val xAxisDataBarChart = AxisData.Builder()
        .axisStepSize(30.dp)
        .steps(barChartData.size - 1)
        .bottomPadding(40.dp)
        .axisLabelAngle(20f)
        .labelData { index -> barChartData[index].label }
        .build()

    val yAxisDataBarChart = AxisData.Builder()
        .steps(yStepSize)
        .labelAndAxisLinePadding(20.dp)
        .axisOffset(20.dp)
        .labelData { index -> (index * (30 / yStepSize)).toString() }
        .build()

    val barChartDataData = BarChartData(
        chartData = barChartData,
        xAxisData = xAxisDataBarChart,
        yAxisData = yAxisDataBarChart
    )

    val listOfPokemons = statsScreenViewModel.pokemon.collectAsState().value
    statsScreenViewModel.loadPokemon()
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item {
            LineChart(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp),
                lineChartData = lineChartData
            )
        }
        item {
            BarChart(modifier = Modifier.height(350.dp), barChartData = barChartDataData)
        }

        items(listOfPokemons) {
            Text(text = it.name)
        }
    }
}