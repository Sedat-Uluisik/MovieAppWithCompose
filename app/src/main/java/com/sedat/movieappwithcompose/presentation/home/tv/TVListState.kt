package com.sedat.movieappwithcompose.presentation.home.tv

import com.sedat.movieappwithcompose.domain.model.TV

sealed class TVListState{
    object IsLoading: TVListState()
    data class IsSuccessful(val data: List<TV>): TVListState()
    data class Error(val message: String): TVListState()
}