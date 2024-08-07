package com.sedat.movieappwithcompose.presentation.home.movie

import androidx.compose.foundation.horizontalScroll
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
import androidx.navigation.NavController
import com.sedat.movieappwithcompose.presentation.home.ViewModelHome

@Composable
fun MovieScreen(
    navController: NavController,
    viewModelHome: ViewModelHome
) {
    var movieCategoryType by rememberSaveable {
        mutableStateOf(MovieCategoryEvent.POPULAR)
    }

    MovieCategorySelector(){
        movieCategoryType = it
    }

    when(movieCategoryType){
        MovieCategoryEvent.POPULAR -> {
            viewModelHome.changeMovieCategory(MovieCategoryEvent.POPULAR)
            PopularMovies(navController = navController, viewModel = viewModelHome)
        }
        MovieCategoryEvent.TOP_RATED -> {
            viewModelHome.changeMovieCategory(MovieCategoryEvent.TOP_RATED)
            TopRatedMovies(navController = navController, viewModel = viewModelHome)
        }
        MovieCategoryEvent.UPCOMING -> {
            viewModelHome.changeMovieCategory(MovieCategoryEvent.UPCOMING)
            UpcomingMovies(navController = navController, viewModel = viewModelHome)
        }
        MovieCategoryEvent.TREND ->{
            viewModelHome.changeMovieCategory(MovieCategoryEvent.TREND)
            TrendMovies(navController = navController, viewModel = viewModelHome)
        }
    }
}

@Composable
fun MovieCategorySelector(selectedCategory: (MovieCategoryEvent) -> Unit) {

    var selectedCategoryState by rememberSaveable {
        mutableStateOf(MovieCategoryEvent.POPULAR)
    }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .horizontalScroll(rememberScrollState())
            .padding(start = 4.dp, end = 4.dp, top = 2.dp, bottom = 2.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Button(
            onClick = {
                selectedCategoryState = MovieCategoryEvent.POPULAR
                selectedCategory.invoke(MovieCategoryEvent.POPULAR)
            },
            shape = RoundedCornerShape(30.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = if (selectedCategoryState == MovieCategoryEvent.POPULAR) Color.DarkGray else Color.Black,
                contentColor = Color.Magenta
            )
        ) {
            Text(text = "Popüler Filmler")
        }

        Spacer(modifier = Modifier.width(4.dp))

        Button(
            onClick = {
                selectedCategoryState = MovieCategoryEvent.TOP_RATED
                selectedCategory.invoke(MovieCategoryEvent.TOP_RATED)
            },
            shape = RoundedCornerShape(30.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = if (selectedCategoryState == MovieCategoryEvent.TOP_RATED) Color.DarkGray else Color.Black,
                contentColor = Color.Magenta
            )
        ) {
            Text(text = "En Çok Oy Alanlar")
        }

        Spacer(modifier = Modifier.width(4.dp))

        Button(
            onClick = {
                selectedCategoryState = MovieCategoryEvent.UPCOMING
                selectedCategory.invoke(MovieCategoryEvent.UPCOMING)
            },
            shape = RoundedCornerShape(30.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = if (selectedCategoryState == MovieCategoryEvent.UPCOMING) Color.DarkGray else Color.Black,
                contentColor = Color.Magenta
            )
        ) {
            Text(text = "Yaklaşan Filmler")
        }

        Spacer(modifier = Modifier.width(4.dp))

        Button(
            onClick = {
                selectedCategoryState = MovieCategoryEvent.TREND
                selectedCategory.invoke(MovieCategoryEvent.TREND)
            },
            shape = RoundedCornerShape(30.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = if (selectedCategoryState == MovieCategoryEvent.TREND) Color.DarkGray else Color.Black,
                contentColor = Color.Magenta
            )
        ) {
            Text(text = "Trend Filmler")
        }
    }
}