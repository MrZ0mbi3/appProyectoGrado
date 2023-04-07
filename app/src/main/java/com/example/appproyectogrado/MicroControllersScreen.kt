package com.example.appproyectogrado

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.appproyectogrado.viewmodel.MicroControllerScreenViewModel
import kotlinx.coroutines.launch

@Composable
fun MicroControllersScreen(navController: NavHostController = rememberNavController()) {
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()
    val microControllerScreenViewModel: MicroControllerScreenViewModel = viewModel()
    Scaffold(modifier = Modifier
        .fillMaxSize()
        .background(color = Color.DarkGray),
        scaffoldState = scaffoldState,
        topBar = {
            TopAppBar {
                IconButton(onClick = { scope.launch { scaffoldState.drawerState.open() } }) {
                    Icon(Icons.Filled.Menu, contentDescription = "burgerBottom")
                }
                Text(text = stringResource(id = R.string.microcontroladores))
            }
        }, drawerContent = {
            DrawerContent(navController)
        }) {
        MicroControllerStateScreen(microControllerScreenViewModel)
    }
}

@Composable
fun MicroControllerStateScreen(microControllerScreenViewModel: MicroControllerScreenViewModel) {
    val cropData = microControllerScreenViewModel.cropData.collectAsState().value
    val stateFirstMicroController =
        microControllerScreenViewModel.stateFirstMicroController.collectAsState().value
    if (cropData.isNotEmpty()) {
        microControllerScreenViewModel.isMicroControllerActive()
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            item {
                Card(
                    Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp, vertical = 50.dp)
                        .wrapContentHeight(), elevation = 2.dp
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentHeight()
                            .padding(all = 10.dp)
                    ) {
                        Text(
                            modifier = Modifier.padding(vertical = 5.dp),
                            text = stringResource(id = R.string.estado_microcontrolador) + "\t" + cropData[0].device
                        )
                        Text(
                            text =
                            when (stateFirstMicroController) {
                                true -> stringResource(id = R.string.activo)
                                false -> stringResource(id = R.string.Inactivo)
                            }
                        )
                        Text(
                            modifier = Modifier.padding(vertical = 5.dp),
                            text = stringResource(id = R.string.hora_ultimo_dato_tomado) + "\t" + cropData[0].publishedAt
                        )
                        Text(
                            modifier = Modifier.padding(vertical = 5.dp),
                            text = stringResource(id = R.string.ultimo_dato_tomado) + "\t" + cropData[0].toString()
                        )
                    }
                }
            }
        }

    }
}