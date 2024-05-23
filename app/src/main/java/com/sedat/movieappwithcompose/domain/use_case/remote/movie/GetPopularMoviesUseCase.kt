package com.sedat.movieappwithcompose.domain.use_case.remote.movie

import com.sedat.movieappwithcompose.data.remote.model.movie.toMovie
import com.sedat.movieappwithcompose.domain.model.Movie
import com.sedat.movieappwithcompose.domain.repository.movie.MovieRepository
import com.sedat.movieappwithcompose.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetPopularMoviesUseCase @Inject constructor(
    private val movieRepository: MovieRepository
) {
    operator fun invoke(page: Int, language: String): Flow<Resource<List<Movie>>> = flow{
        try {
            emit(Resource.loading(null))

            val data = movieRepository.getMovies(page, language)

            if(data.results.isNotEmpty())
                emit(Resource.success(data.toMovie()))
            else
                emit(Resource.error("Movies Not Found!", null))
        }catch (e: Exception){
            emit(Resource.error("Connection Error", null))
        }
    }
}