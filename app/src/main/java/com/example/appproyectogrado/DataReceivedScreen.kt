package com.example.appproyectogrado

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.appproyectogrado.viewmodel.StatsScreenViewModel
import kotlinx.coroutines.launch

@Composable
fun DataReceivedScreen(navController: NavHostController = rememberNavController()){
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
                Text(text = stringResource(id = R.string.datosrecibidos))
            }
        }, drawerContent = {
            DrawerContent(navController)
        }) {
        Stats(statsScreenViewModel)
    }

}