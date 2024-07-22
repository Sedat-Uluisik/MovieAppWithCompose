package com.sedat.movieappwithcompose.presentation.home.tv

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController

@Composable
fun TrendTVsScreen(
    navController: NavController,
    viewModelTVs: ViewModelTVs
) {
    val trendTVs = viewModelTVs.tvListState.value

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ){
        
    }
}