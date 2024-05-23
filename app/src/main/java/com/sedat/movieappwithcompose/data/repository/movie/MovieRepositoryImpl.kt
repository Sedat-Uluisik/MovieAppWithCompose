package com.sedat.movieappwithcompose.data.repository.movie

import com.sedat.movieappwithcompose.data.remote.MovieAPI
import com.sedat.movieappwithcompose.data.remote.model.movie.MovieDto
import com.sedat.movieappwithcompose.domain.repository.movie.MovieRepository
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val movieAPI: MovieAPI
): MovieRepository {
    override suspend fun getMovies(page: Int, language: String): MovieDto {
        return movieAPI.getMovies(page, language)
    }
}