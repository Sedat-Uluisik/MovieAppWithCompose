package com.sedat.movieappwithcompose.presentation.home.movie

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.sedat.movieappwithcompose.domain.model.Movie
import com.sedat.movieappwithcompose.domain.model.getMoviePosterUrl
import com.sedat.movieappwithcompose.presentation.home.ViewModelHome

@Composable
fun UpcomingMovies(
    navController: NavController,
    viewModel: ViewModelHome = hiltViewModel()
) {

    LaunchedEffect(key1 = Unit){
        viewModel.getUpcomingMovies()
    }

    val popularMovies = viewModel.movieListState.value

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
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
                        MovieListRow(movie = it)
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

