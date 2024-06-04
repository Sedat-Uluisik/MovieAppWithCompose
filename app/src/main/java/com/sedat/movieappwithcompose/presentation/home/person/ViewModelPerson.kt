package com.sedat.movieappwithcompose.presentation.home.person

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sedat.movieappwithcompose.domain.use_case.remote.person.GetPopularPersonsUseCase
import com.sedat.movieappwithcompose.util.Status
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class ViewModelPerson @Inject constructor(
    private val getPopularPersonsUseCase: GetPopularPersonsUseCase
): ViewModel() {

    private val _popularPersons = mutableStateOf<PersonListState>(PersonListState.IsLoading)
    val popularPersons: State<PersonListState> = _popularPersons

    init {
        getPopularPeoples()
    }

    private fun getPopularPeoples(){
        getPopularPersonsUseCase.invoke(page = 1, language = "tr").onEach {
            when(it.status){
                Status.LOADING -> _popularPersons.value = PersonListState.IsLoading
                Status.SUCCESS -> _popularPersons.value = PersonListState.IsSuccessful(it.data ?: listOf())
                Status.ERROR -> _popularPersons.value = PersonListState.Error(message = it.message ?: "Error")
            }
        }.launchIn(viewModelScope)
    }

}