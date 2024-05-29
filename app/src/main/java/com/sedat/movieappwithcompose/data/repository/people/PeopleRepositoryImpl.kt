package com.sedat.movieappwithcompose.data.repository.people

import com.sedat.movieappwithcompose.data.remote.MovieAPI
import com.sedat.movieappwithcompose.data.remote.model.people.PeopleDto
import com.sedat.movieappwithcompose.domain.repository.people.PeopleRepository
import javax.inject.Inject

class PeopleRepositoryImpl @Inject constructor(
    private val movieAPI: MovieAPI
): PeopleRepository {
    override suspend fun getPopularPeoples(page: Int, language: String): PeopleDto {
        return movieAPI.getPopularPersons(language = language, page = page)
    }

}