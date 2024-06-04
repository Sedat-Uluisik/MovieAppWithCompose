package com.sedat.movieappwithcompose.domain.use_case.remote.person

import com.sedat.movieappwithcompose.data.remote.model.person.PeopleResult
import com.sedat.movieappwithcompose.domain.repository.people.PersonRepository
import com.sedat.movieappwithcompose.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetPopularPersonsUseCase @Inject constructor(
    private val personRepository: PersonRepository
) {
    operator fun invoke(page: Int, language: String): Flow<Resource<List<PeopleResult>>> = flow{
        try {
            emit(Resource.loading(null))

            val data = personRepository.getPopularPeoples(page = page, language = language)

            emit(Resource.success(data.results))

        }catch (e: Exception){
            emit(Resource.error("Connection or App Error", null))
        }
    }
}