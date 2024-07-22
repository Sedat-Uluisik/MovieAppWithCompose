package com.sedat.movieappwithcompose.domain.use_case.remote.tv_series

import com.sedat.movieappwithcompose.data.remote.model.tv.toTV
import com.sedat.movieappwithcompose.domain.model.TV
import com.sedat.movieappwithcompose.domain.repository.tv_series.TvSeriesRepository
import com.sedat.movieappwithcompose.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetTrendTVsUseCase @Inject constructor(
    private val tvSeriesRepository: TvSeriesRepository
) {
    operator fun invoke(time: String, region: String, page: Int, language: String): Flow<Resource<List<TV>>> = flow{
        try {
            emit(Resource.loading(null))

            val data = tvSeriesRepository.getTrendTVs(time = time, region = region, page = page, language = language)

            if(data.results.isNotEmpty())
                emit(Resource.success(data.toTV()))
            else
                emit(Resource.error("Trend TVs Not Found!", null))
        }catch (e: Exception){
            emit(Resource.error("Connection Error Or App Error", null))
        }
    }
}