package com.example.appproyectogrado

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.appproyectogrado.ui.theme.ui.theme.AppProyectoGradoTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppProyectoGradoTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    MyAppNavHost()
                }
            }
        }
    }
}

@Composable
fun MyAppNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String = "FirstScreen"
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination
    ) {
        composable(startDestination) { FirstScreen(navController = navController) }
        composable("StatsScreen") { StatsScreen(navController = navController) }
        composable("MicrocontrollersScreen") { MicroControllersScreen(navController = navController) }
        composable("DataReceivedScreen") { DataReceivedScreen(navController = navController) }
    }
}

@Composable
fun BackGround() {
    Column(Modifier.fillMaxSize(), verticalArrangement = Arrangement.Bottom) {
        Image(
            modifier = Modifier
                .fillMaxSize()
                .alpha(0.2f),
            painter = painterResource(id = R.drawable.plant_first_screen),
            contentDescription = "homePageBackGround",
            contentScale = ContentScale.FillHeight
        )
    }

}

@Composable
fun DrawerContent(navController: NavHostController = rememberNavController()) {
    Column(modifier = Modifier.fillMaxSize()) {
        Button(modifier = Modifier
            .fillMaxWidth()
            .padding(30.dp),
            colors = ButtonDefaults.textButtonColors(
                backgroundColor = Color.White,
                contentColor = Color.Black
            ),
            onClick = { navController.navigate("FirstScreen") }) {
            Text(color = Color.Black, text = stringResource(id = R.string.inicio))
        }
        Button(modifier = Modifier
            .fillMaxWidth()
            .padding(30.dp),
            colors = ButtonDefaults.textButtonColors(
                backgroundColor = Color.White,
                contentColor = Color.Black
            ),
            onClick = { navController.navigate("StatsScreen") }) {
            Text(color = Color.Black, text = stringResource(id = R.string.estadisticas))
        }
        Button(modifier = Modifier
            .fillMaxWidth()
            .padding(30.dp),
            colors = ButtonDefaults.textButtonColors(
                backgroundColor = Color.White,
                contentColor = Color.Black
            ),
            onClick = { navController.navigate("MicrocontrollersScreen") }) {
            Text(color = Color.Black, text = stringResource(id = R.string.microcontroladores))
        }
        Button(modifier = Modifier
            .fillMaxWidth()
            .padding(30.dp),
            colors = ButtonDefaults.textButtonColors(
                backgroundColor = Color.White,
                contentColor = Color.Black
            ),
            onClick = { navController.navigate("DataReceivedScreen") }) {
            Text(color = Color.Black, text = stringResource(id = R.string.datosrecibidos))
        }
    }
}

@Composable
fun FirstScreen(
    navController: NavHostController = rememberNavController(),
) {
    BackGround()
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            modifier = Modifier.padding(top = 24.dp, bottom = 36.dp),
            text = "Hola! \n Bienvenido",
            fontSize = 55.sp
        )
        Text(
            modifier = Modifier.padding(bottom = 36.dp),
            text = "Conectando con la base de datos...",
            fontSize = 20.sp
        )
        Button(modifier = Modifier.padding(vertical = 36.dp), onClick = {
            navController.navigate("StatsScreen") { popUpTo("FirstScreen") }
        }) {
            Text(text = "Entrar")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    AppProyectoGradoTheme {
        FirstScreen()
    }
}