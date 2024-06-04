package com.sedat.movieappwithcompose.presentation.home

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.sedat.movieappwithcompose.presentation.home.movie.MovieScreen
import com.sedat.movieappwithcompose.presentation.home.person.PersonScreen
import kotlin.math.roundToInt

@Composable
fun Home(
    navController: NavController,
    viewModelHome: ViewModelHome = hiltViewModel()
) {

    var mainCategoryType by rememberSaveable {
        mutableStateOf(MainCategoryEvent.MOVIE)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {

        MainCategorySelector(){
            mainCategoryType = it
        }
        when(mainCategoryType){
            MainCategoryEvent.MOVIE -> MovieScreen(navController = navController, viewModelHome = viewModelHome)
            MainCategoryEvent.TV -> MovieScreen(navController = navController, viewModelHome = viewModelHome)
            MainCategoryEvent.PEOPLE -> PersonScreen()
        }
    }
}

@Composable
fun MainCategorySelector(selectedMainCategory: (MainCategoryEvent) -> Unit) {

    var selectedTab by remember { mutableStateOf(0) }
    val tabTitles = listOf("Movie", "TV", "People")

    BoxWithConstraints(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFFC73333), shape = RoundedCornerShape(16.dp))
            .padding(start = 4.dp, end = 4.dp)
    ) {
        val screenWidth = constraints.maxWidth.toFloat()
        val tabWidth = screenWidth / tabTitles.size

        val transition = updateTransition(targetState = selectedTab, label = "Tab Transition")

        val offsetX by transition.animateFloat(
            transitionSpec = { tween(durationMillis = 500) },
            label = "Offset Animation"
        ) { state ->
            ((tabWidth) * state)
        }

        var boxWidthPx by remember { mutableStateOf(0f) }
        val density = LocalDensity.current

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp)
                .padding(top = 4.dp, bottom = 4.dp)
                .onGloballyPositioned { layoutCoordinates ->
                    boxWidthPx = layoutCoordinates.size.width.toFloat()
                }
        ) {
            val boxWidthDp = with(density) { boxWidthPx.toDp() }
            Box(
                modifier = Modifier
                    .offset { IntOffset(offsetX.roundToInt(), 0) }
                    .width(boxWidthDp / 3)
                    .fillMaxHeight()
                    .clip(RoundedCornerShape(16.dp))
                    .background(Color.White)
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
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
                            selectedMainCategory.invoke(
                                when(index){
                                    0 -> MainCategoryEvent.MOVIE
                                    1 -> MainCategoryEvent.TV
                                    else -> MainCategoryEvent.PEOPLE
                                }
                            )
                        },
                        modifier = Modifier.width(boxWidthDp / 3),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.Transparent,
                        ),
                        contentPadding = PaddingValues(8.dp)
                    ) {
                        Text(
                            text = title,
                            color = textColor,
                            fontSize = 16.sp
                        )
                    }
                }
            }
        }
    }
}


