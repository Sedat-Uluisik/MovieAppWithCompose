package com.sedat.movieappwithcompose.data.repository.people

import com.sedat.movieappwithcompose.data.remote.MovieAPI
import com.sedat.movieappwithcompose.data.remote.model.person.PersonDto
import com.sedat.movieappwithcompose.domain.repository.people.PersonRepository
import javax.inject.Inject

class PersonRepositoryImpl @Inject constructor(
    private val movieAPI: MovieAPI
): PersonRepository {
    override suspend fun getPopularPeoples(page: Int, language: String): PersonDto {
        return movieAPI.getPopularPersons(language = language, page = page)
    }

}