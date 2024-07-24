package com.sedat.movieappwithcompose.presentation.home.tv

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.sedat.movieappwithcompose.presentation.home.movie.CustomSwitchButton

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
        when(trendTVs){
            is TVListState.IsLoading ->{
                CircularProgressIndicator(color = Color.White)
            }
            is TVListState.IsSuccessful ->{
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                ){
                    items(trendTVs.data){
                        TVListRow(tv = it)
                    }
                }
            }
            is TVListState.Error ->{
                Text(text = trendTVs.message)
            }
        }
        Box(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(8.dp)
        ){
            CustomSwitchButton{
                viewModelTVs.getTrendTvs(it)
            }
        }
    }
}