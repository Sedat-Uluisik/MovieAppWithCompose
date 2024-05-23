package com.sedat.movieappwithcompose.domain.repository.movie

import com.sedat.movieappwithcompose.data.remote.model.movie.MovieDto

interface MovieRepository {
    suspend fun getMovies(page: Int, language: String): MovieDto
}