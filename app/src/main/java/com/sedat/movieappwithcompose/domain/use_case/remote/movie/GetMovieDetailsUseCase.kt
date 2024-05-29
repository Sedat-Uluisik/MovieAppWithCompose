package com.sedat.movieappwithcompose.domain.use_case.remote.movie

import com.sedat.movieappwithcompose.data.remote.model.toMovieDetails
import com.sedat.movieappwithcompose.domain.model.MovieDetails
import com.sedat.movieappwithcompose.domain.repository.movie.MovieRepository
import com.sedat.movieappwithcompose.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetMovieDetailsUseCase @Inject constructor(
    private val movieRepository: MovieRepository
) {
    operator fun invoke(movieID: Int, language: String): Flow<Resource<MovieDetails>> = flow{
        try {
            emit(Resource.loading(null))

            val data = movieRepository.getMovieDetails(movieId = movieID, language = language)

            data.body()?.let {
                emit(Resource.success(it.toMovieDetails()))
            } ?: emit(Resource.error("Movie details is not get", null))

        }catch (e: Exception){
            emit(Resource.error("Connection or App Error", null))
        }
    }
}