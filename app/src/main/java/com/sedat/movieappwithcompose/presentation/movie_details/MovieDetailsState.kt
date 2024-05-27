package com.sedat.movieappwithcompose.presentation.movie_details

import com.sedat.movieappwithcompose.domain.model.MovieDetails

sealed class MovieDetailsState{
    object IsLoading: MovieDetailsState()
    data class IsSuccessful(val data: MovieDetails?): MovieDetailsState()
    data class Error(val message: String): MovieDetailsState()
}