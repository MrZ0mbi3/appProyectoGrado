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
    val cropData = statsScreenViewModel.cropData.collectAsState().value
    val hum01ChartLineData = statsScreenViewModel.chartPointsHum01.collectAsState().value
    var lineChartData: LineChartData? = null
    if (hum01ChartLineData.isNotEmpty()) {
        val xAxisData = AxisData.Builder()
            .axisStepSize(30.dp)
            .steps(30)
            .labelData { i -> i.toString() }
            .labelAndAxisLinePadding(15.dp)
            .build()

        val yAxisData = AxisData.Builder()
            .labelAndAxisLinePadding(15.dp)
            .labelData { i ->
                // Add yMin to get the negative axis values to the scale
                val yMin = hum01ChartLineData.minOf { it.y }
                val yMax = hum01ChartLineData.maxOf { it.y }
                val yScale = (yMax - yMin) / 1f
                ((i * yScale) + yMin).formatToSinglePrecision()
            }.build()
        lineChartData = LineChartData(
            linePlotData = LinePlotData(
                lines = listOf(
                    Line(
                        dataPoints = hum01ChartLineData,
                        LineStyle(),
                        IntersectionPoint(),
                        SelectionHighlightPoint(color = Color.Gray),
                        ShadowUnderLine(color = Color.Cyan),
                        SelectionHighlightPopUp()
                    )
                ),
            ),
            xAxisData = xAxisData,
            yAxisData = yAxisData,
            gridLines = GridLines()
        )
    }
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        lineChartData?.let {
            item { Text(text = stringResource(id = R.string.grafica_hum01)) }
            item {
                LineChart(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(300.dp),
                    lineChartData = lineChartData
                )
            }
        }
        items(cropData) {
            Text(text = it.toString())
        }
    }
}