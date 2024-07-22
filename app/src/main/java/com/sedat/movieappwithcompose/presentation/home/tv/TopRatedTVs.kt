package com.sedat.movieappwithcompose.presentation.home.tv

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
import androidx.navigation.NavController

@Composable
fun TopRatedTVs(
    navController: NavController,
    viewModelTVs: ViewModelTVs
) {
    val popularTVs = viewModelTVs.tvListState.value

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ){

        when(popularTVs){
            is TVListState.IsLoading ->{
                CircularProgressIndicator(color = Color.White)
            }
            is TVListState.IsSuccessful ->{
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                ){
                    items(
                        items = popularTVs.data,
                        key = { popularTVs -> popularTVs.id }
                    ){
                        TVListRow(tv = it)
                    }
                }
            }
            is TVListState.Error ->{
                Text(text = popularTVs.message)
            }
        }
    }
}