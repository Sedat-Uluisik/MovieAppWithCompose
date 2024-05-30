package com.sedat.movieappwithcompose.presentation.home.peoples

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun PeoplesScreen(
    viewModelPeoples: ViewModelPeoples = hiltViewModel()
) {

    val popularPeoples = viewModelPeoples.popularPersons.value

    Box(
        modifier = Modifier
            .fillMaxSize()
    ){
        when(popularPeoples){
            is PeopleListState.IsLoading ->{
                CircularProgressIndicator(color = Color.Yellow)
            }
            is PeopleListState.IsSuccessful ->{
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                ){
                    items(popularPeoples.data){
                        PeopleListRow(peopleResult = it)
                    }
                }
            }
            is PeopleListState.Error ->{
                Text(text = popularPeoples.message, fontSize = 25.sp, color = Color.Red)
            }
        }
    }
}