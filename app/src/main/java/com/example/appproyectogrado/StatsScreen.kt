package com.example.appproyectogrado

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
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

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
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
    val cropDataD1 = statsScreenViewModel.cropDataD1.collectAsState().value
    val cropDataD2 = statsScreenViewModel.cropDataD2.collectAsState().value
    val cropDataD3 = statsScreenViewModel.cropDataD3.collectAsState().value
    var lineChartHum01D1: LineChartData? = null
    var lineChartHum01D2: LineChartData? = null
    var lineChartHum01D3: LineChartData? = null
    var lineChartLuxD1: LineChartData? = null
    var lineChartLuxD2: LineChartData? = null
    var lineChartLuxD3: LineChartData? = null
    var lineChartTempD1: LineChartData? = null
    var lineChartTempD2: LineChartData? = null
    var lineChartTempD3: LineChartData? = null

    val xAxisData = AxisData.Builder()
        .axisStepSize(30.dp)
        .steps(30)
        .labelData { i -> i.toString() }
        .labelAndAxisLinePadding(15.dp)
        .build()
    if (cropDataD1.isNotEmpty()) {
        val humDevice1 = statsScreenViewModel.makeListPointsHum01(cropDataD1)
        val yAxisDataHum01 = AxisData.Builder()
            .labelAndAxisLinePadding(15.dp)
            .labelData { i ->
                // Add yMin to get the negative axis values to the scale
                val yMin = humDevice1.minOf { it.y }
                val yMax = humDevice1.maxOf { it.y }
                val yScale = (yMax - yMin) / 1f
                ((i * yScale) + yMin).formatToSinglePrecision()
            }.build()
        lineChartHum01D1 = LineChartData(
            linePlotData = LinePlotData(
                lines = listOf(
                    Line(
                        dataPoints = humDevice1,
                        LineStyle(),
                        IntersectionPoint(),
                        SelectionHighlightPoint(color = Color.Gray),
                        ShadowUnderLine(color = Color.Cyan),
                        SelectionHighlightPopUp()
                    )
                ),
            ),
            xAxisData = xAxisData,
            yAxisData = yAxisDataHum01,
            gridLines = GridLines()
        )
        val luxDevice1 = statsScreenViewModel.makeListPointsLight(cropDataD1)
        val yAxisDataLux1 = AxisData.Builder()
            .labelAndAxisLinePadding(15.dp)
            .labelData { i ->
                // Add yMin to get the negative axis values to the scale
                val yMin = luxDevice1.minOf { it.y }
                val yMax = luxDevice1.maxOf { it.y }
                val yScale = (yMax - yMin) / 1f
                ((i * yScale) + yMin).formatToSinglePrecision()
            }.build()
        lineChartLuxD1 = LineChartData(
            linePlotData = LinePlotData(
                lines = listOf(
                    Line(
                        dataPoints = luxDevice1,
                        LineStyle(),
                        IntersectionPoint(),
                        SelectionHighlightPoint(color = Color.Gray),
                        ShadowUnderLine(color = Color.Yellow),
                        SelectionHighlightPopUp()
                    )
                ),
            ),
            xAxisData = xAxisData,
            yAxisData = yAxisDataLux1,
            gridLines = GridLines()
        )

        val tempDevice1 = statsScreenViewModel.makeListPointsTemp(cropDataD1)
        val yAxisDataTemp1 = AxisData.Builder()
            .labelAndAxisLinePadding(15.dp)
            .labelData { i ->
                // Add yMin to get the negative axis values to the scale
                val yMin = tempDevice1.minOf { it.y }
                val yMax = tempDevice1.maxOf { it.y }
                val yScale = (yMax - yMin) / 1f
                ((i * yScale) + yMin).formatToSinglePrecision()
            }.build()
        lineChartTempD1 = LineChartData(
            linePlotData = LinePlotData(
                lines = listOf(
                    Line(
                        dataPoints = tempDevice1,
                        LineStyle(),
                        IntersectionPoint(),
                        SelectionHighlightPoint(color = Color.Gray),
                        ShadowUnderLine(color = Color.Red),
                        SelectionHighlightPopUp()
                    )
                ),
            ),
            xAxisData = xAxisData,
            yAxisData = yAxisDataTemp1,
            gridLines = GridLines()
        )
    }

    if (cropDataD2.isNotEmpty()) {
        val humDevice2 = statsScreenViewModel.makeListPointsHum01(cropDataD2)
        val yAxisDataHum2 = AxisData.Builder()
            .labelAndAxisLinePadding(15.dp)
            .labelData { i ->
                // Add yMin to get the negative axis values to the scale
                val yMin = humDevice2.minOf { it.y }
                val yMax = humDevice2.maxOf { it.y }
                val yScale = (yMax - yMin) / 1f
                ((i * yScale) + yMin).formatToSinglePrecision()
            }.build()
        lineChartHum01D2 = LineChartData(
            linePlotData = LinePlotData(
                lines = listOf(
                    Line(
                        dataPoints = humDevice2,
                        LineStyle(),
                        IntersectionPoint(),
                        SelectionHighlightPoint(color = Color.Gray),
                        ShadowUnderLine(color = Color.Cyan),
                        SelectionHighlightPopUp()
                    )
                ),
            ),
            xAxisData = xAxisData,
            yAxisData = yAxisDataHum2,
            gridLines = GridLines()
        )
        val luxDevice2 = statsScreenViewModel.makeListPointsLight(cropDataD2)
        val yAxisDataLux2 = AxisData.Builder()
            .labelAndAxisLinePadding(15.dp)
            .labelData { i ->
                // Add yMin to get the negative axis values to the scale
                val yMin = luxDevice2.minOf { it.y }
                val yMax = luxDevice2.maxOf { it.y }
                val yScale = (yMax - yMin) / 1f
                ((i * yScale) + yMin).formatToSinglePrecision()
            }.build()
        lineChartLuxD2 = LineChartData(
            linePlotData = LinePlotData(
                lines = listOf(
                    Line(
                        dataPoints = luxDevice2,
                        LineStyle(),
                        IntersectionPoint(),
                        SelectionHighlightPoint(color = Color.Gray),
                        ShadowUnderLine(color = Color.Yellow),
                        SelectionHighlightPopUp()
                    )
                ),
            ),
            xAxisData = xAxisData,
            yAxisData = yAxisDataLux2,
            gridLines = GridLines()
        )

        val tempDevice2 = statsScreenViewModel.makeListPointsTemp(cropDataD2)
        val yAxisDataTemp2 = AxisData.Builder()
            .labelAndAxisLinePadding(15.dp)
            .labelData { i ->
                // Add yMin to get the negative axis values to the scale
                val yMin = tempDevice2.minOf { it.y }
                val yMax = tempDevice2.maxOf { it.y }
                val yScale = (yMax - yMin) / 1f
                ((i * yScale) + yMin).formatToSinglePrecision()
            }.build()
        lineChartTempD2 = LineChartData(
            linePlotData = LinePlotData(
                lines = listOf(
                    Line(
                        dataPoints = tempDevice2,
                        LineStyle(),
                        IntersectionPoint(),
                        SelectionHighlightPoint(color = Color.Gray),
                        ShadowUnderLine(color = Color.Red),
                        SelectionHighlightPopUp()
                    )
                ),
            ),
            xAxisData = xAxisData,
            yAxisData = yAxisDataTemp2,
            gridLines = GridLines()
        )
    }

    if (cropDataD3.isNotEmpty()) {
        val humDevice3 = statsScreenViewModel.makeListPointsHum01(cropDataD3)
        val yAxisDataHum3 = AxisData.Builder()
            .labelAndAxisLinePadding(15.dp)
            .labelData { i ->
                // Add yMin to get the negative axis values to the scale
                val yMin = humDevice3.minOf { it.y }
                val yMax = humDevice3.maxOf { it.y }
                val yScale = (yMax - yMin) / 1f
                ((i * yScale) + yMin).formatToSinglePrecision()
            }.build()
        lineChartHum01D3 = LineChartData(
            linePlotData = LinePlotData(
                lines = listOf(
                    Line(
                        dataPoints = humDevice3,
                        LineStyle(),
                        IntersectionPoint(),
                        SelectionHighlightPoint(color = Color.Gray),
                        ShadowUnderLine(color = Color.Cyan),
                        SelectionHighlightPopUp()
                    )
                ),
            ),
            xAxisData = xAxisData,
            yAxisData = yAxisDataHum3,
            gridLines = GridLines()
        )
        val luxDevice3 = statsScreenViewModel.makeListPointsLight(cropDataD2)
        val yAxisDataLux3 = AxisData.Builder()
            .labelAndAxisLinePadding(15.dp)
            .labelData { i ->
                // Add yMin to get the negative axis values to the scale
                val yMin = luxDevice3.minOf { it.y }
                val yMax = luxDevice3.maxOf { it.y }
                val yScale = (yMax - yMin) / 1f
                ((i * yScale) + yMin).formatToSinglePrecision()
            }.build()
        lineChartLuxD3 = LineChartData(
            linePlotData = LinePlotData(
                lines = listOf(
                    Line(
                        dataPoints = luxDevice3,
                        LineStyle(),
                        IntersectionPoint(),
                        SelectionHighlightPoint(color = Color.Gray),
                        ShadowUnderLine(color = Color.Yellow),
                        SelectionHighlightPopUp()
                    )
                ),
            ),
            xAxisData = xAxisData,
            yAxisData = yAxisDataLux3,
            gridLines = GridLines()
        )

        val tempDevice3 = statsScreenViewModel.makeListPointsTemp(cropDataD3)
        val yAxisDataTemp3 = AxisData.Builder()
            .labelAndAxisLinePadding(15.dp)
            .labelData { i ->
                // Add yMin to get the negative axis values to the scale
                val yMin = tempDevice3.minOf { it.y }
                val yMax = tempDevice3.maxOf { it.y }
                val yScale = (yMax - yMin) / 1f
                ((i * yScale) + yMin).formatToSinglePrecision()
            }.build()
        lineChartTempD3 = LineChartData(
            linePlotData = LinePlotData(
                lines = listOf(
                    Line(
                        dataPoints = tempDevice3,
                        LineStyle(),
                        IntersectionPoint(),
                        SelectionHighlightPoint(color = Color.Gray),
                        ShadowUnderLine(color = Color.Red),
                        SelectionHighlightPopUp()
                    )
                ),
            ),
            xAxisData = xAxisData,
            yAxisData = yAxisDataTemp3,
            gridLines = GridLines()
        )
    }


    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.Start
    ) {
        if (cropDataD1.isNotEmpty()) {
            item {
                Text(
                    modifier = Modifier
                        .padding(vertical = 20.dp)
                        .testTag("deviceNameDevice1"),
                    text = cropDataD1[0].device,
                    fontWeight = FontWeight.Bold
                )
            }
            lineChartHum01D1?.let {
                item {
                    Text(
                        text = stringResource(id = R.string.grafica_hum01),
                        modifier = Modifier.testTag("lineChartHum01Device1"),
                        textAlign = TextAlign.Center
                    )
                }
                item {
                    LineChart(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(300.dp),
                        lineChartData = it
                    )
                }
            }
            lineChartLuxD1?.let {
                item {
                    Text(
                        text = stringResource(id = R.string.grafica_lux),
                        modifier = Modifier.testTag("lineChartLuxDevice1"),
                        textAlign = TextAlign.Center
                    )
                }
                item {
                    LineChart(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(300.dp),
                        lineChartData = it
                    )
                }
            }
            lineChartTempD1?.let {
                item {
                    Text(
                        text = stringResource(id = R.string.grafica_temp),
                        modifier = Modifier.testTag("lineChartTempDevice1"),
                        textAlign = TextAlign.Center
                    )
                }
                item {
                    LineChart(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(300.dp),
                        lineChartData = it
                    )
                }
            }
        }
        if (cropDataD2.isNotEmpty()) {
            item {
                Text(
                    modifier = Modifier
                        .padding(vertical = 20.dp)
                        .testTag("deviceNameDevice2"),
                    text = cropDataD2[0].device,
                    fontWeight = FontWeight.Bold
                )
            }
            lineChartHum01D2?.let {
                item {
                    Text(
                        text = stringResource(id = R.string.grafica_hum01),
                        modifier = Modifier.testTag("lineChartHum01Device2"),
                        textAlign = TextAlign.Center
                    )
                }
                item {
                    LineChart(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(300.dp),
                        lineChartData = it
                    )
                }
            }
            lineChartLuxD2?.let {
                item {
                    Text(
                        text = stringResource(id = R.string.grafica_lux),
                        modifier = Modifier.testTag("lineChartLuxDevice2"),
                        textAlign = TextAlign.Center
                    )
                }
                item {
                    LineChart(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(300.dp),
                        lineChartData = it
                    )
                }
            }
            lineChartTempD2?.let {
                item {
                    Text(
                        text = stringResource(id = R.string.grafica_temp),
                        modifier = Modifier.testTag("lineChartTempDevice2"),
                        textAlign = TextAlign.Center
                    )
                }
                item {
                    LineChart(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(300.dp),
                        lineChartData = it
                    )
                }
            }
        }
        if (cropDataD3.isNotEmpty()) {
            item {
                Text(
                    modifier = Modifier
                        .padding(vertical = 20.dp)
                        .testTag("deviceNameDevice3"),
                    text = cropDataD3[0].device,
                    fontWeight = FontWeight.Bold
                )
            }
            lineChartHum01D3?.let {
                item {
                    Text(
                        text = stringResource(id = R.string.grafica_hum01),
                        modifier = Modifier.testTag("lineChartHum01Device3"),
                        textAlign = TextAlign.Center
                    )
                }
                item {
                    LineChart(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(300.dp),
                        lineChartData = it
                    )
                }
            }
            lineChartLuxD3?.let {
                item {
                    Text(
                        text = stringResource(id = R.string.grafica_lux),
                        modifier = Modifier.testTag("lineChartLuxDevice3"),
                        textAlign = TextAlign.Center
                    )
                }
                item {
                    LineChart(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(300.dp),
                        lineChartData = it
                    )
                }
            }
            lineChartTempD3?.let {
                item {
                    Text(
                        text = stringResource(id = R.string.grafica_temp),
                        modifier = Modifier.testTag("lineChartTempDevice3"),
                        textAlign = TextAlign.Center
                    )
                }
                item {
                    LineChart(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(300.dp),
                        lineChartData = it
                    )
                }
            }
        }
    }
}