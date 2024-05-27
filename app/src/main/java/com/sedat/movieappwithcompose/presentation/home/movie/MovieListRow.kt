package com.sedat.movieappwithcompose.presentation.home.movie

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import coil.compose.rememberImagePainter
import com.sedat.movieappwithcompose.domain.model.Movie
import com.sedat.movieappwithcompose.domain.model.getMoviePosterUrl

@Composable
fun MovieListRow(
    movie: Movie
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        /*AsyncImage(
            model = movie.getMoviePosterUrl(),
            contentDescription = null,
            modifier = Modifier
                .padding(4.dp)
                .size(200.dp)
                .background(Color.Red)
        )*/

        Image(
            painter = rememberAsyncImagePainter(model = movie.getMoviePosterUrl()),
            contentDescription = null,
            modifier = Modifier
                .padding(4.dp)
                .size(200.dp)
                .background(Color.Red)
        )

        Column {
            Text(
                text = movie.title
            )
            Text(
                text = movie.releaseDate
            )
            Text(
                text = movie.voteAverage.toString()
            )
        }
    }
}