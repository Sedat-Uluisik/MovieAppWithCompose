package com.sedat.movieappwithcompose.presentation.home.person

import com.sedat.movieappwithcompose.data.remote.model.person.PeopleResult

sealed class PersonListState{
    object IsLoading: PersonListState()
    data class IsSuccessful(val data: List<PeopleResult>): PersonListState()
    data class Error(val message: String): PersonListState()
}