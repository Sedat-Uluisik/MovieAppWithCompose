package com.sedat.movieappwithcompose.presentation.home

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
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
import com.sedat.movieappwithcompose.presentation.home.movie.MovieCategoryEvent
import com.sedat.movieappwithcompose.presentation.home.movie.PopularMovies
import com.sedat.movieappwithcompose.presentation.home.movie.TopRatedMovies
import com.sedat.movieappwithcompose.presentation.home.movie.UpcomingMovies

@Composable
fun Home(
    navController: NavController
) {

    var movieCategoryType by rememberSaveable {
        mutableStateOf(MovieCategoryEvent.POPULAR)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {

        MovieCategorySelector(){
            movieCategoryType = it
        }

        when(movieCategoryType){
            MovieCategoryEvent.POPULAR -> PopularMovies(navController = navController)
            MovieCategoryEvent.TOP_RATED -> TopRatedMovies(navController = navController)
            MovieCategoryEvent.UPCOMING -> UpcomingMovies(navController = navController)
        }
    }


}

@Composable
fun MovieCategorySelector(selected_category: (MovieCategoryEvent) -> Unit) {

    var selectedCategory by rememberSaveable {
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
                selectedCategory = MovieCategoryEvent.POPULAR
                selected_category.invoke(MovieCategoryEvent.POPULAR)
            },
            shape = RoundedCornerShape(30.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = if (selectedCategory == MovieCategoryEvent.POPULAR) Color.DarkGray else Color.Black,
                contentColor = Color.Magenta
            )
        ) {
            Text(text = "Popüler Filmler")
        }

        Spacer(modifier = Modifier.width(4.dp))

        Button(
            onClick = {
                selectedCategory = MovieCategoryEvent.TOP_RATED
                selected_category.invoke(MovieCategoryEvent.TOP_RATED)
            },
            shape = RoundedCornerShape(30.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = if (selectedCategory == MovieCategoryEvent.TOP_RATED) Color.DarkGray else Color.Black,
                contentColor = Color.Magenta
            )
        ) {
            Text(text = "En Çok Oy Alanlar")
        }

        Spacer(modifier = Modifier.width(4.dp))

        Button(
            onClick = {
                selectedCategory = MovieCategoryEvent.UPCOMING
                selected_category.invoke(MovieCategoryEvent.UPCOMING)
            },
            shape = RoundedCornerShape(30.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = if (selectedCategory == MovieCategoryEvent.UPCOMING) Color.DarkGray else Color.Black,
                contentColor = Color.Magenta
            )
        ) {
            Text(text = "Yaklaşan Filmler")
        }

        println("recompose")
    }
}

