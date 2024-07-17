package com.sedat.movieappwithcompose.presentation.home.tv

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sedat.movieappwithcompose.domain.use_case.remote.tv_series.GetPopularTVsUseCase
import com.sedat.movieappwithcompose.presentation.home.movie.MovieCategoryEvent
import com.sedat.movieappwithcompose.presentation.home.movie.MovieListState
import com.sedat.movieappwithcompose.util.Status
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class ViewModelTVs @Inject constructor(
    private val getPopularTVsUseCase: GetPopularTVsUseCase
): ViewModel() {
    private val _tvListState = mutableStateOf<TVListState>(TVListState.IsLoading)
    val tvListState: State<TVListState> = _tvListState

    private fun getPopularTvs(){
        getPopularTVsUseCase.invoke(page = 1, language = "en").onEach {
            when(it.status){
                Status.LOADING ->{
                    _tvListState.value = TVListState.IsLoading
                }
                Status.SUCCESS ->{
                    _tvListState.value = TVListState.IsSuccessful(it.data ?: listOf())
                }
                Status.ERROR ->{
                    _tvListState.value = TVListState.Error(message = it.message ?: "Error!")
                }
            }
        }.launchIn(viewModelScope)
    }

    private fun getTopRatedTvs(){  //düzenlenecek
        getPopularTVsUseCase.invoke(page = 1, language = "en").onEach {
            when(it.status){
                Status.LOADING ->{
                    _tvListState.value = TVListState.IsLoading
                }
                Status.SUCCESS ->{
                    _tvListState.value = TVListState.IsSuccessful(it.data ?: listOf())
                }
                Status.ERROR ->{
                    _tvListState.value = TVListState.Error(message = it.message ?: "Error!")
                }
            }
        }.launchIn(viewModelScope)
    }

    fun changeTvCategory(tvCategoryEvent: TVCategoryEvent){
        when(tvCategoryEvent){
            TVCategoryEvent.POPULAR -> getPopularTvs()
            TVCategoryEvent.TOP_RATED -> getTopRatedTvs()
        }
    }
}