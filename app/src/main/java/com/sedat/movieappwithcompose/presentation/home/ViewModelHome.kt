package com.sedat.movieappwithcompose.presentation.home

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sedat.movieappwithcompose.domain.use_case.remote.movie.GetPopularMoviesUseCase
import com.sedat.movieappwithcompose.util.Status
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class ViewModelHome @Inject constructor(
    private val getPopularMoviesUseCase: GetPopularMoviesUseCase
): ViewModel() {

    private val _popularMovieListState = mutableStateOf<MovieListState>(MovieListState.Nothing)
    val popularMovieListState: State<MovieListState> = _popularMovieListState

    init {
        getPopularMovieList()
    }

    fun getPopularMovieList(){
        getPopularMoviesUseCase.invoke(page = 1, language = "en").onEach {
            when(it.status){
                Status.LOADING ->{
                    _popularMovieListState.value = MovieListState.IsLoading
                }
                Status.SUCCESS ->{
                    _popularMovieListState.value = MovieListState.IsSuccessful(it.data ?: listOf())
                }
                Status.ERROR ->{
                    _popularMovieListState.value = MovieListState.Error(message = it.message ?: "Error!")
                }

            }
        }.launchIn(viewModelScope)

    }
}