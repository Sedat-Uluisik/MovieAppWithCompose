package com.sedat.movieappwithcompose.presentation.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController

@Composable
fun Home(
    navController: NavController,
    viewModel: ViewModelHome = hiltViewModel()
) {
    val popularMovies = viewModel.popularMovieListState.value

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
    ){
        when(popularMovies){
            is MovieListState.IsLoading ->{
                CircularProgressIndicator(color = Color.White)
            }
            is MovieListState.IsSuccessful ->{
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                ){
                    items(popularMovies.data){
                        Text(text = it.title, color = Color.White)
                    }
                }
            }
            is MovieListState.Error ->{
                Text(text = popularMovies.message)
            }
            else ->{

            }
        }
    }
}