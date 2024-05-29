package com.sedat.movieappwithcompose.domain.repository.people

import com.sedat.movieappwithcompose.data.remote.model.people.PeopleDto

interface PeopleRepository {
    suspend fun getPopularPeoples(page: Int, language: String): PeopleDto
}