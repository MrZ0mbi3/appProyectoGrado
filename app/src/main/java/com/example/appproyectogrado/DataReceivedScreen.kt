package com.example.appproyectogrado

import android.annotation.SuppressLint
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
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.appproyectogrado.viewmodel.DataReceivedScreenViewModel
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun DataReceivedScreen(navController: NavHostController = rememberNavController()) {
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()
    val dataReceivedScreenViewModel: DataReceivedScreenViewModel = viewModel()
    Scaffold(modifier = Modifier
        .fillMaxSize()
        .background(color = Color.DarkGray),
        scaffoldState = scaffoldState,
        topBar = {
            TopAppBar {
                IconButton(onClick = { scope.launch { scaffoldState.drawerState.open() } }) {
                    Icon(Icons.Filled.Menu, contentDescription = "burgerBottom")
                }
                Text(text = stringResource(id = R.string.datosrecibidos))
            }
        }, drawerContent = {
            DrawerContent(navController)
        }) {
        DataReceived(dataReceivedScreenViewModel = dataReceivedScreenViewModel)
    }

}

@Composable
fun DataReceived(dataReceivedScreenViewModel: DataReceivedScreenViewModel) {
    val cropDataD1 = dataReceivedScreenViewModel.cropDataD1.collectAsState().value
    val cropDataD2 = dataReceivedScreenViewModel.cropDataD2.collectAsState().value
    val cropDataD3 = dataReceivedScreenViewModel.cropDataD3.collectAsState().value
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item {
            Text(
                modifier = Modifier.padding(vertical = 15.dp),
                text = stringResource(id = R.string.datos_recibidos_24horas)
            )
        }
        if (cropDataD1.isNotEmpty()) {
            item {
                Text(text = cropDataD1[0].device, modifier = Modifier.testTag("dataReceivedD1"))
            }
        }

        items(cropDataD1) {
            Card(
                Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp, vertical = 10.dp)
                    .wrapContentHeight(), elevation = 2.dp
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .padding(all = 10.dp)
                ) {
                    Text(text = "Fecha: " + it.publishedAt)
                    Text(text = "Humedad01: " + it.hum01)
                    Text(text = "Humedad02: " + it.hum02)
                    Text(text = "Hygro: " + it.hygro01)
                    Text(text = "luz: " + it.lux01)
                    Text(text = "Temperatura 1:" + it.temp01)
                    Text(text = "Temperatura 2:" + it.temp02)
                }
            }
        }
        if (cropDataD2.isNotEmpty()) {
            item {
                Text(text = cropDataD2[0].device, modifier = Modifier.testTag("dataReceivedD2"))
            }
        }
        items(cropDataD2) {
            Card(
                Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp, vertical = 10.dp)
                    .wrapContentHeight(), elevation = 2.dp
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .padding(all = 10.dp)
                ) {
                    Text(text = "Fecha: " + it.publishedAt)
                    Text(text = "Humedad01: " + it.hum01)
                    Text(text = "Humedad02: " + it.hum02)
                    Text(text = "Hygro: " + it.hygro01)
                    Text(text = "luz: " + it.lux01)
                    Text(text = "Temperatura 1:" + it.temp01)
                    Text(text = "Temperatura 2:" + it.temp02)
                }
            }
        }
        if (cropDataD3.isNotEmpty()) {
            item {
                Text(text = cropDataD2[0].device, modifier = Modifier.testTag("dataReceivedD3"))
            }
        }
        items(cropDataD3) {
            Card(
                Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp, vertical = 10.dp)
                    .wrapContentHeight(), elevation = 2.dp
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .padding(all = 10.dp)
                ) {
                    Text(text = "Fecha: " + it.publishedAt)
                    Text(text = "Humedad01: " + it.hum01)
                    Text(text = "Humedad02: " + it.hum02)
                    Text(text = "Hygro: " + it.hygro01)
                    Text(text = "luz: " + it.lux01)
                    Text(text = "Temperatura 1:" + it.temp01)
                    Text(text = "Temperatura 2:" + it.temp02)
                }
            }
        }
    }
}