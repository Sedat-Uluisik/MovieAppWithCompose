package com.sedat.movieappwithcompose.domain.repository.tv_series

import com.sedat.movieappwithcompose.data.remote.model.tv.TVDto

interface TvSeriesRepository {
    suspend fun getPopularTVs(page: Int, language: String): TVDto
}