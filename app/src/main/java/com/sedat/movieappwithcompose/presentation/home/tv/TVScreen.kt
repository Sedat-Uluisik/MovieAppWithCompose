package com.sedat.movieappwithcompose.presentation.home.tv

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController

@Composable
fun TVScreen(
    navController: NavController,
    viewModelTVs: ViewModelTVs = hiltViewModel()
) {
    var tvCategoryType by rememberSaveable {
        mutableStateOf(TVCategoryEvent.POPULAR)
    }

    TVCategorySelector(){
        tvCategoryType = it
    }

    when(tvCategoryType){
        TVCategoryEvent.POPULAR -> {
            viewModelTVs.changeTvCategory(TVCategoryEvent.POPULAR)
            PopularTVs(navController = navController, viewModelTVs = viewModelTVs)
        }
        TVCategoryEvent.TOP_RATED -> {
            viewModelTVs.changeTvCategory(TVCategoryEvent.TOP_RATED)
            TopRatedTVs(navController = navController, viewModelTVs = viewModelTVs)
        }
        TVCategoryEvent.TREND ->{
            viewModelTVs.changeTvCategory(TVCategoryEvent.TREND)
            TrendTVsScreen(navController = navController, viewModelTVs = viewModelTVs)
        }
    }
}

@Composable
fun TVCategorySelector(selectedCategory: (TVCategoryEvent) -> Unit) {

    var selectedCategoryState by rememberSaveable {
        mutableStateOf(TVCategoryEvent.POPULAR)
    }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .horizontalScroll(rememberScrollState())
            .padding(start = 4.dp, end = 4.dp, top = 2.dp, bottom = 2.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        Button(
            onClick = {
                selectedCategoryState = TVCategoryEvent.POPULAR
                selectedCategory.invoke(TVCategoryEvent.POPULAR)
            },
            shape = RoundedCornerShape(30.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = if (selectedCategoryState == TVCategoryEvent.POPULAR) Color.DarkGray else Color.Black,
                contentColor = Color.Magenta
            )
        ) {
            Text(text = "Popüler Filmler")
        }

        Spacer(modifier = Modifier.width(4.dp))

        Button(
            onClick = {
                selectedCategoryState = TVCategoryEvent.TOP_RATED
                selectedCategory.invoke(TVCategoryEvent.TOP_RATED)
            },
            shape = RoundedCornerShape(30.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = if (selectedCategoryState == TVCategoryEvent.TOP_RATED) Color.DarkGray else Color.Black,
                contentColor = Color.Magenta
            )
        ) {
            Text(text = "En Çok Oy Alanlar")
        }

        Spacer(modifier = Modifier.width(4.dp))

        Button(
            onClick = {
                selectedCategoryState = TVCategoryEvent.TREND
                selectedCategory.invoke(TVCategoryEvent.TREND)
            },
            shape = RoundedCornerShape(30.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = if (selectedCategoryState == TVCategoryEvent.TREND) Color.DarkGray else Color.Black,
                contentColor = Color.Magenta
            )
        ) {
            Text(text = "Trend TV Programları")
        }
    }
}