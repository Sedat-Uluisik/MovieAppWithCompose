package com.sedat.movieappwithcompose.presentation.movie_details

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sedat.movieappwithcompose.domain.use_case.remote.movie.GetMovieDetailsUseCase
import com.sedat.movieappwithcompose.util.Constants.Companion.MOVIE_ID
import com.sedat.movieappwithcompose.util.Status
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class ViewModelMovieDetails @Inject constructor(
    private val getMovieDetailsUseCase: GetMovieDetailsUseCase,
    private val stateHandle: SavedStateHandle
): ViewModel() {

    private val _movieDetailState = mutableStateOf<MovieDetailsState>(MovieDetailsState.IsLoading)
    val movieDetailState: State<MovieDetailsState> get() = _movieDetailState

    init {
        stateHandle.get<Int>(MOVIE_ID)?.let {
            getMovieDetail(movieID = it)
        }
    }

    private fun getMovieDetail(movieID: Int){
        getMovieDetailsUseCase.invoke(movieID = movieID, language = "en").onEach {
            when(it.status){
                Status.LOADING ->{
                    _movieDetailState.value = MovieDetailsState.IsLoading
                }
                Status.SUCCESS ->{
                    _movieDetailState.value = MovieDetailsState.IsSuccessful(data = it.data)
                }
                Status.ERROR ->{
                    _movieDetailState.value = MovieDetailsState.Error(message = it.message ?: "Movie Detail is not getting!")
                }
            }
        }.launchIn(viewModelScope)
    }

}