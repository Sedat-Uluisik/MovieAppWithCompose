package com.sedat.movieappwithcompose.presentation.home

import com.sedat.movieappwithcompose.domain.model.Movie

sealed class MovieListState{
    object Nothing: MovieListState()
    object IsLoading: MovieListState()
    data class IsSuccessful(val data: List<Movie>): MovieListState()
    data class Error(val message: String): MovieListState()
}