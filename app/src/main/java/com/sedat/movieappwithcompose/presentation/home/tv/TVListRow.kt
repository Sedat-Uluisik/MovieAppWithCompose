package com.sedat.movieappwithcompose.presentation.home.tv

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImagePainter
import coil.compose.SubcomposeAsyncImage
import coil.compose.SubcomposeAsyncImageContent
import com.sedat.movieappwithcompose.domain.model.TV
import com.sedat.movieappwithcompose.domain.model.getMoviePosterUrl
import com.sedat.movieappwithcompose.domain.model.getTVPosterUrl

@Composable
fun TVListRow(
    tv: TV
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        SubcomposeAsyncImage(
            model = tv.getTVPosterUrl(),
            contentDescription = null,
            modifier = Modifier
                .padding(4.dp)
                .size(200.dp)
                .background(Color.Red)
        ) {
            val state = painter.state
            if (state is AsyncImagePainter.State.Loading || state is AsyncImagePainter.State.Error) {
                CircularProgressIndicator()
            } else {
                SubcomposeAsyncImageContent()
            }
        }

        Column {
            Text(
                text = tv.title
            )
            Text(
                text = tv.firstAirDate
            )
            Text(
                text = tv.voteAverage.toString()
            )
        }
    }
}