package com.sedat.movieappwithcompose.presentation.home.person

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
fun PersonScreen(
    viewModelPerson: ViewModelPerson = hiltViewModel()
) {

    val popularPeoples = viewModelPerson.popularPersons.value

    Box(
        modifier = Modifier
            .fillMaxSize()
    ){
        when(popularPeoples){
            is PersonListState.IsLoading ->{
                CircularProgressIndicator(color = Color.Yellow)
            }
            is PersonListState.IsSuccessful ->{
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                ){
                    items(
                        items = popularPeoples.data,
                        key = {
                            it.originalName
                        }
                    ){
                        PersonListRow(peopleResult = it)
                    }
                }
            }
            is PersonListState.Error ->{
                Text(text = popularPeoples.message, fontSize = 25.sp, color = Color.Red)
            }
        }
    }
}