package com.sedat.movieappwithcompose.presentation.home.movie

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.sedat.movieappwithcompose.presentation.home.ViewModelHome
import kotlin.math.roundToInt

@Composable
fun TrendMovies(
    navController: NavController,
    viewModel: ViewModelHome
) {

    val popularMovies = viewModel.movieListState.value

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ){
        when(popularMovies){
            is MovieListState.IsLoading ->{
                CircularProgressIndicator(color = Color.White)
            }
            is MovieListState.IsSuccessful ->{
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                ){
                    items(popularMovies.data){
                        MovieListRow(movie = it)
                    }
                }
            }
            is MovieListState.Error ->{
                Text(text = popularMovies.message)
            }
        }
        Box(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(8.dp),
            contentAlignment = Alignment.Center
        ) {
            CustomSwitchButton{
                viewModel.getTrendMovies(it)
            }
        }
    }
}

@Composable
fun CustomSwitchButton(onCheckedChange: (Boolean) -> Unit) {
    var selectedTab by remember { mutableStateOf(0) }
    val tabTitles = listOf("Day", "Week")

    val screenWidth2 = LocalConfiguration.current.screenWidthDp

    BoxWithConstraints(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 4.dp, end = 4.dp)
    ) {
        val screenWidth = constraints.maxWidth.toFloat()
        val tabWidth = (screenWidth / 4) / tabTitles.size

        val transition = updateTransition(targetState = selectedTab, label = "Tab Transition")

        val offsetX by transition.animateFloat(
            transitionSpec = { tween(durationMillis = 500) },
            label = "Offset Animation"
        ) { state ->
            if(state == 1)
                ((tabWidth + 10f) * state)
            else
                ((tabWidth) * state)
        }

        Box(
            modifier = Modifier
                .width((screenWidth2 * 0.25).dp)
                .height(48.dp)
                .padding(top = 4.dp, bottom = 4.dp)
                .background(Color(0xFFC73333), shape = RoundedCornerShape(16.dp))
                .align(Alignment.BottomEnd)
        ) {
            Box(
                modifier = Modifier
                    .offset { IntOffset(offsetX.roundToInt(), 0) }
                    .width(((screenWidth2 * 0.25) / 2).dp)
                    .padding(4.dp)
                    .fillMaxHeight()
                    .clip(RoundedCornerShape(16.dp))
                    .background(Color.White)
            )
            Row(
                modifier = Modifier,
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                tabTitles.forEachIndexed { index, title ->
                    val textColor by animateColorAsState(
                        targetValue = if (selectedTab == index) Color.Blue else Color.Gray,
                        animationSpec = tween(durationMillis = 500),
                        label = ""
                    )

                    Button(
                        onClick = {
                            selectedTab = index
                            onCheckedChange.invoke(
                                when(index){
                                    0 -> true
                                    1 -> false
                                    else -> true
                                }
                            )
                        },
                        modifier = Modifier.width(((screenWidth2 * 0.25) / 2).dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.Transparent,
                        ),
                        contentPadding = PaddingValues(8.dp)
                    ) {
                        Text(
                            text = title,
                            color = textColor,
                            fontSize = 12.sp
                        )
                    }
                }
            }
        }
    }
}



