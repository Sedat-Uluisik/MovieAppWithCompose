package com.sedat.movieappwithcompose.presentation.home

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.EaseIn
import androidx.compose.animation.core.EaseOut
import androidx.compose.animation.core.tween
import androidx.compose.animation.togetherWith
import androidx.compose.animation.with
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.sedat.movieappwithcompose.presentation.home.movie.MovieCategoryEvent
import com.sedat.movieappwithcompose.presentation.home.movie.PopularMovies
import com.sedat.movieappwithcompose.presentation.home.movie.TopRatedMovies
import com.sedat.movieappwithcompose.presentation.home.movie.UpcomingMovies

@Composable
fun Home(
    navController: NavController,
    viewModelHome: ViewModelHome = hiltViewModel()
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

        MainCategorySelector()

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
    }
}

@Composable
fun MainCategorySelector() {

    var movieCategoryType by rememberSaveable {
        mutableStateOf(MainCategoryEvent.MOVIE)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {

        aa(){
            movieCategoryType = it
        }

        AnimatedContent(
            targetState = movieCategoryType,
            label = "",
            transitionSpec = {
                slideIntoContainer(
                    animationSpec = tween(3000, easing = EaseIn),
                    towards = AnimatedContentTransitionScope.SlideDirection.Up
                ).togetherWith(
                    slideOutOfContainer(
                        animationSpec = tween(3000, easing = EaseOut),
                        towards = AnimatedContentTransitionScope.SlideDirection.Down
                    )
                )
            }
        ) {
            when(it){
                MainCategoryEvent.MOVIE -> {

                }
                MainCategoryEvent.TV ->{

                }
                MainCategoryEvent.PEOPLE ->{

                }
            }
        }
    }

}

@Composable
fun aa(selected_category: (MainCategoryEvent) -> Unit) {

    var selectedCategory by rememberSaveable {
        mutableStateOf(MainCategoryEvent.MOVIE)
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
                selectedCategory = MainCategoryEvent.MOVIE
                selected_category.invoke(MainCategoryEvent.MOVIE)
            },
            shape = RoundedCornerShape(30.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = if (selectedCategory == MainCategoryEvent.MOVIE) Color.DarkGray else Color.Black,
                contentColor = Color.Magenta
            )
        ) {
            Text(text = "Popüler Filmler")
        }

        Spacer(modifier = Modifier.width(4.dp))

        Button(
            onClick = {
                selectedCategory = MainCategoryEvent.TV
                selected_category.invoke(MainCategoryEvent.TV)
            },
            shape = RoundedCornerShape(30.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = if (selectedCategory == MainCategoryEvent.TV) Color.DarkGray else Color.Black,
                contentColor = Color.Magenta
            )
        ) {
            Text(text = "En Çok Oy Alanlar")
        }

        Spacer(modifier = Modifier.width(4.dp))

        Button(
            onClick = {
                selectedCategory = MainCategoryEvent.PEOPLE
                selected_category.invoke(MainCategoryEvent.PEOPLE)
            },
            shape = RoundedCornerShape(30.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = if (selectedCategory == MainCategoryEvent.PEOPLE) Color.DarkGray else Color.Black,
                contentColor = Color.Magenta
            )
        ) {
            Text(text = "Yaklaşan Filmler")
        }
    }
}

