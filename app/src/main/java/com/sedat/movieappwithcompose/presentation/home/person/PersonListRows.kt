package com.sedat.movieappwithcompose.presentation.home.person

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImagePainter
import coil.compose.SubcomposeAsyncImage
import coil.compose.SubcomposeAsyncImageContent
import com.sedat.movieappwithcompose.data.remote.model.getName
import com.sedat.movieappwithcompose.data.remote.model.person.PeopleResult
import com.sedat.movieappwithcompose.data.remote.model.person.getPosterUrl

@Composable
fun PersonListRow(
    peopleResult: PeopleResult
) {
    Box(
        modifier = Modifier
            .padding(4.dp)
    ){
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .weight(0.5f),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                SubcomposeAsyncImage(
                    model = peopleResult.getPosterUrl(),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .padding(4.dp)
                        .size(90.dp)
                        .clip(CircleShape)
                        .border(2.dp, Color.Black, CircleShape)
                ) {
                    val state = painter.state
                    if (state is AsyncImagePainter.State.Loading || state is AsyncImagePainter.State.Error) {
                        CircularProgressIndicator()
                    } else {
                        SubcomposeAsyncImageContent()
                    }
                }
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = peopleResult.name,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center
                )
            }

            LazyRow(
                modifier = Modifier
                    .weight(1f),
                verticalAlignment = Alignment.CenterVertically
            ){
                items(peopleResult.knownFor){
                    ImageCarousel(it.getImageUrl(), it.getName())
                }
            }
        }
    }
}

@Composable
fun ImageCarousel(url: String, movieTitle: String) {

    val colorStops = arrayOf(
        0.0f to Color.Transparent,
        1f to Color(0xFF242424)
    )

    Box(
        modifier = Modifier
            .height(200.dp)
            .width(150.dp)
            .padding(4.dp),
        contentAlignment = Alignment.Center
    ) {
        SubcomposeAsyncImage(
            model = url,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxSize()
                .clip(RoundedCornerShape(16.dp))
                .border(2.dp, Color.Black, RoundedCornerShape(16.dp))
        ) {
            val state = painter.state
            if (state is AsyncImagePainter.State.Loading || state is AsyncImagePainter.State.Error) {
                CircularProgressIndicator()
            } else {
                SubcomposeAsyncImageContent()
            }
        }

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.verticalGradient(
                        colorStops = colorStops
                    )
                ),
            contentAlignment = Alignment.BottomCenter
        ){
            Text(
                modifier = Modifier
                    .padding(bottom = 12.dp)
                    .alpha(0.6f),
                text = movieTitle,
                color = Color.White,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                fontSize = 16.sp
            )
        }
    }
}