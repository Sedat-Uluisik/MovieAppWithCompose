package com.sedat.movieappwithcompose.data.repository.movie

import com.sedat.movieappwithcompose.data.remote.MovieAPI
import com.sedat.movieappwithcompose.data.remote.model.movie.MovieDto
import com.sedat.movieappwithcompose.data.remote.model.movie.Result
import com.sedat.movieappwithcompose.domain.repository.movie.MovieRepository
import retrofit2.Response
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val movieAPI: MovieAPI
): MovieRepository {
    override suspend fun getMovies(page: Int, language: String): MovieDto {
        return movieAPI.getPopularMovies(page = page, language =  language)
    }

    override suspend fun getTopRatedMovies(page: Int, language: String): MovieDto {
        return movieAPI.getTopRatedMovies(language = language, page =  page)
    }

    override suspend fun getUpcomingMovies(page: Int, language: String, region: String): MovieDto {
        return movieAPI.getUpcomingMovies(language = language, page = page, region = region)
    }

    override suspend fun getMovieDetails(movieId: Int, language: String): Response<Result> {
        return movieAPI.getMovieDetails(movieId = movieId, language= language)
    }
}