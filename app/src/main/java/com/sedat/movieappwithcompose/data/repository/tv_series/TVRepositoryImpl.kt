package com.sedat.movieappwithcompose.data.repository.tv_series

import com.sedat.movieappwithcompose.data.remote.MovieAPI
import com.sedat.movieappwithcompose.data.remote.model.person.PersonDto
import com.sedat.movieappwithcompose.data.remote.model.tv.TVDto
import com.sedat.movieappwithcompose.domain.repository.people.PersonRepository
import com.sedat.movieappwithcompose.domain.repository.tv_series.TvSeriesRepository
import javax.inject.Inject

class TVRepositoryImpl @Inject constructor(
    private val movieAPI: MovieAPI
): TvSeriesRepository {
    override suspend fun getPopularTVs(page: Int, language: String): TVDto {
        return movieAPI.getPopularTVs(language = language, page = page)
    }

    override suspend fun getTopRatedTVs(page: Int, language: String): TVDto {
        return movieAPI.getTopRatedTVs(language, page)
    }

    override suspend fun getTrendTVs(
        time: String,
        region: String,
        page: Int,
        language: String
    ): TVDto {
        return movieAPI.getTrendTVs(time, language, region, page)
    }
}