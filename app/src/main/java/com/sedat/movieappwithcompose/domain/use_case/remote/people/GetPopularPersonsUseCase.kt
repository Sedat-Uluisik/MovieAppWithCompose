package com.sedat.movieappwithcompose.domain.use_case.remote.people

import com.sedat.movieappwithcompose.data.remote.model.people.PeopleDto
import com.sedat.movieappwithcompose.data.remote.model.people.PeopleResult
import com.sedat.movieappwithcompose.data.remote.model.toMovieDetails
import com.sedat.movieappwithcompose.domain.model.MovieDetails
import com.sedat.movieappwithcompose.domain.repository.movie.MovieRepository
import com.sedat.movieappwithcompose.domain.repository.people.PeopleRepository
import com.sedat.movieappwithcompose.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetPopularPersonsUseCase @Inject constructor(
    private val peopleRepository: PeopleRepository
) {
    operator fun invoke(page: Int, language: String): Flow<Resource<List<PeopleResult>>> = flow{
        try {
            emit(Resource.loading(null))

            val data = peopleRepository.getPopularPeoples(page = page, language = language)

            emit(Resource.success(data.results))

        }catch (e: Exception){
            emit(Resource.error("Connection or App Error", null))
        }
    }
}