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
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.appproyectogrado.viewmodel.MicroControllerScreenViewModel
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
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
    val cropDataD1 = microControllerScreenViewModel.cropDataD1.collectAsState().value
    val cropDataD2 = microControllerScreenViewModel.cropDataD2.collectAsState().value
    val cropDataD3 = microControllerScreenViewModel.cropDataD3.collectAsState().value
    val stateFirstMicroController =
        microControllerScreenViewModel.stateFirstMicroController.collectAsState().value
    val stateSecondMicroController =
        microControllerScreenViewModel.stateSecondMicroController.collectAsState().value
    val stateThirdMicroController =
        microControllerScreenViewModel.stateThirdMicroController.collectAsState().value
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        if (cropDataD1.isNotEmpty()) {
            microControllerScreenViewModel.isMicroControllerActive()
            item {
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
                        Text(
                            modifier = Modifier
                                .padding(vertical = 5.dp)
                                .testTag("microcontrollerOneDevice"),
                            text = stringResource(id = R.string.estado_microcontrolador) + "\t" + cropDataD1[0].device
                        )
                        Text(
                            modifier = Modifier
                                .padding(vertical = 5.dp)
                                .testTag("microcontrollerOneState"),
                            text =
                            when (stateFirstMicroController) {
                                true -> stringResource(id = R.string.activo)
                                false -> stringResource(id = R.string.Inactivo)
                            }
                        )
                        Text(
                            modifier = Modifier
                                .padding(vertical = 5.dp)
                                .testTag("lastDataDateMicrocontrollerOne"),
                            text = stringResource(id = R.string.hora_ultimo_dato_tomado) + "\t" + cropDataD1[0].publishedAt
                        )
                        Text(
                            modifier = Modifier
                                .padding(vertical = 5.dp)
                                .testTag("lastDataMicrocontrollerOne"),
                            text = stringResource(id = R.string.ultimo_dato_tomado) + "\t" + cropDataD1[0].toString()
                        )
                    }
                }
            }
        }
        if (cropDataD2.isNotEmpty()) {
            microControllerScreenViewModel.isMicroControllerActive()
            item {
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
                        Text(
                            modifier = Modifier
                                .padding(vertical = 5.dp)
                                .testTag("microcontrollerTwoDevice"),
                            text = stringResource(id = R.string.estado_microcontrolador) + "\t" + cropDataD2[0].device
                        )
                        Text(
                            modifier = Modifier
                                .padding(vertical = 5.dp)
                                .testTag("microcontrollerTwoState"),
                            text =
                            when (stateSecondMicroController) {
                                true -> stringResource(id = R.string.activo)
                                false -> stringResource(id = R.string.Inactivo)
                            }
                        )
                        Text(
                            modifier = Modifier
                                .padding(vertical = 5.dp)
                                .testTag("lastDataDateMicrocontrollerTwo"),
                            text = stringResource(id = R.string.hora_ultimo_dato_tomado) + "\t" + cropDataD2[0].publishedAt
                        )
                        Text(
                            modifier = Modifier
                                .padding(vertical = 5.dp)
                                .testTag("lastDataMicrocontrollerTwo"),
                            text = stringResource(id = R.string.ultimo_dato_tomado) + "\t" + cropDataD2[0].toString()
                        )
                    }
                }
            }
        }
        if (cropDataD3.isNotEmpty()) {
            microControllerScreenViewModel.isMicroControllerActive()
            item {
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
                        Text(
                            modifier = Modifier
                                .padding(vertical = 5.dp)
                                .testTag("microcontrollerThreeDevice"),
                            text = stringResource(id = R.string.estado_microcontrolador) + "\t" + cropDataD3[0].device
                        )
                        Text(
                            modifier = Modifier
                                .padding(vertical = 5.dp)
                                .testTag("microcontrollerThreeState"),
                            text =
                            when (stateThirdMicroController) {
                                true -> stringResource(id = R.string.activo)
                                false -> stringResource(id = R.string.Inactivo)
                            }
                        )
                        Text(
                            modifier = Modifier
                                .padding(vertical = 5.dp)
                                .testTag("lastDataDateMicrocontrollerThree"),
                            text = stringResource(id = R.string.hora_ultimo_dato_tomado) + "\t" + cropDataD3[0].publishedAt
                        )
                        Text(
                            modifier = Modifier
                                .padding(vertical = 5.dp)
                                .testTag("lastDataMicrocontrollerThree"),
                            text = stringResource(id = R.string.ultimo_dato_tomado) + "\t" + cropDataD3[0].toString()
                        )
                    }
                }
            }
        }
    }

}