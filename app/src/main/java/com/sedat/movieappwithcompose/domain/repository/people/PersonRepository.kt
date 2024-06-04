package com.sedat.movieappwithcompose.domain.repository.people

import com.sedat.movieappwithcompose.data.remote.model.person.PersonDto

interface PersonRepository {
    suspend fun getPopularPeoples(page: Int, language: String): PersonDto
}