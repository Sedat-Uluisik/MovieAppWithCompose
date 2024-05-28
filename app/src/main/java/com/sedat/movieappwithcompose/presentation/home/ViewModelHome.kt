package com.sedat.movieappwithcompose.presentation.home

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sedat.movieappwithcompose.domain.use_case.remote.movie.GetPopularMoviesUseCase
import com.sedat.movieappwithcompose.domain.use_case.remote.movie.GetTopRatedMoviesUseCase
import com.sedat.movieappwithcompose.domain.use_case.remote.movie.GetUpcomingMoviesUseCase
import com.sedat.movieappwithcompose.presentation.home.movie.MovieCategoryEvent
import com.sedat.movieappwithcompose.presentation.home.movie.MovieListState
import com.sedat.movieappwithcompose.util.Status
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class ViewModelHome @Inject constructor(
    private val getPopularMoviesUseCase: GetPopularMoviesUseCase,
    private val getTopRatedMoviesUseCase: GetTopRatedMoviesUseCase,
    private val getUpcomingMoviesUseCase: GetUpcomingMoviesUseCase
): ViewModel() {

    private val _movieListState = mutableStateOf<MovieListState>(MovieListState.IsLoading)
    val movieListState: State<MovieListState> = _movieListState

    private fun getPopularMovies(){
        getPopularMoviesUseCase.invoke(page = 1, language = "en").onEach {
            when(it.status){
                Status.LOADING ->{
                    _movieListState.value = MovieListState.IsLoading
                }
                Status.SUCCESS ->{
                    _movieListState.value = MovieListState.IsSuccessful(it.data ?: listOf())
                }
                Status.ERROR ->{
                    _movieListState.value = MovieListState.Error(message = it.message ?: "Error!")
                }
            }
        }.launchIn(viewModelScope)
    }

    private fun getTopRatedMovies(){
        getTopRatedMoviesUseCase.invoke(page = 1, language = "en").onEach {
            when(it.status){
                Status.LOADING ->{
                    _movieListState.value = MovieListState.IsLoading
                }
                Status.SUCCESS ->{
                    _movieListState.value = MovieListState.IsSuccessful(it.data ?: listOf())
                }
                Status.ERROR ->{
                    _movieListState.value = MovieListState.Error(message = it.message ?: "Error!")
                }

            }
        }.launchIn(viewModelScope)
    }

    private fun getUpcomingMovies(){
        getUpcomingMoviesUseCase.invoke(page = 1, language = "TR").onEach {
            when(it.status){
                Status.LOADING ->{
                    _movieListState.value = MovieListState.IsLoading
                }
                Status.SUCCESS ->{
                    _movieListState.value = MovieListState.IsSuccessful(it.data ?: listOf())
                }
                Status.ERROR ->{
                    _movieListState.value = MovieListState.Error(message = it.message ?: "Error!")
                }

            }
        }.launchIn(viewModelScope)
    }

    fun changeMovieCategory(movieCategoryEvent: MovieCategoryEvent){
        when(movieCategoryEvent){
            MovieCategoryEvent.POPULAR -> getPopularMovies()
            MovieCategoryEvent.TOP_RATED -> getTopRatedMovies()
            MovieCategoryEvent.UPCOMING -> getUpcomingMovies()
        }
    }
}