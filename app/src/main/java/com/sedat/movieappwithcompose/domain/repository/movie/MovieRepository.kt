package com.sedat.movieappwithcompose.domain.repository.movie

import com.sedat.movieappwithcompose.data.remote.model.movie.MovieDto
import com.sedat.movieappwithcompose.data.remote.model.movie.Result
import com.sedat.movieappwithcompose.util.Constants
import retrofit2.Response

interface MovieRepository {
    suspend fun getMovies(page: Int, language: String): MovieDto
    suspend fun getTopRatedMovies(page: Int, language: String): MovieDto
    suspend fun getUpcomingMovies(page: Int, language: String, region: String): MovieDto

    suspend fun getMovieDetails(movieId: Int, language: String): Response<Result>
}