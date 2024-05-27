package com.sedat.movieappwithcompose.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.sedat.movieappwithcompose.presentation.home.Home
import com.sedat.movieappwithcompose.presentation.ui.theme.MovieAppWithComposeTheme
import com.sedat.movieappwithcompose.util.Constants.Companion.MOVIE_ID
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MovieAppWithComposeTheme {
                Surface(
                    modifier = Modifier.fillMaxSize()
                ) {
                    NavControllerApp()
                }
            }
        }
    }
}

@Composable
fun NavControllerApp() {
    val navController = rememberNavController()
    
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route
    ){
        composable(
            route = Screen.Home.route
        ){
            Home(navController = navController)
        }

        composable(
            route = Screen.MovieDetail.route + "/${MOVIE_ID}"
        ){

        }
    }
}
