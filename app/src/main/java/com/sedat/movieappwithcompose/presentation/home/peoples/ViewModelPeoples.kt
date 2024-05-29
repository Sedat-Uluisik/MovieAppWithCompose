package com.sedat.movieappwithcompose.presentation.home.peoples

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sedat.movieappwithcompose.domain.use_case.remote.people.GetPopularPersonsUseCase
import com.sedat.movieappwithcompose.util.Status
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class ViewModelPeoples @Inject constructor(
    private val getPopularPersonsUseCase: GetPopularPersonsUseCase
): ViewModel() {

    private val _popularPersons = mutableStateOf<PeopleListState>(PeopleListState.IsLoading)
    val popularPersons: State<PeopleListState> = _popularPersons

    init {
        getPopularPeoples()
    }

    private fun getPopularPeoples(){
        getPopularPersonsUseCase.invoke(page = 1, language = "tr").onEach {
            when(it.status){
                Status.LOADING -> _popularPersons.value = PeopleListState.IsLoading
                Status.SUCCESS -> _popularPersons.value = PeopleListState.IsSuccessful(it.data ?: listOf())
                Status.ERROR -> _popularPersons.value = PeopleListState.Error(message = it.message ?: "Error")
            }
        }.launchIn(viewModelScope)
    }

}