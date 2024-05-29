package com.sedat.movieappwithcompose.presentation.home.peoples

import com.sedat.movieappwithcompose.data.remote.model.people.PeopleResult
import com.sedat.movieappwithcompose.domain.model.Movie

sealed class PeopleListState{
    object IsLoading: PeopleListState()
    data class IsSuccessful(val data: List<PeopleResult>): PeopleListState()
    data class Error(val message: String): PeopleListState()
}