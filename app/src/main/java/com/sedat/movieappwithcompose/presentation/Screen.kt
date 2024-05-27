package com.sedat.movieappwithcompose.presentation

sealed class Screen(val route: String){
    object Home: Screen("Home")
    object MovieDetail: Screen("MovieDetail")
}