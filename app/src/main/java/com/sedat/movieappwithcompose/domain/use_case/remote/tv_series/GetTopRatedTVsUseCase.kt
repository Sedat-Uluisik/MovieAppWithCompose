package com.sedat.movieappwithcompose.domain.use_case.remote.tv_series

import com.sedat.movieappwithcompose.data.remote.model.tv.toTV
import com.sedat.movieappwithcompose.domain.model.TV
import com.sedat.movieappwithcompose.domain.repository.tv_series.TvSeriesRepository
import com.sedat.movieappwithcompose.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetTopRatedTVsUseCase @Inject constructor(
    private val tvSeriesRepository: TvSeriesRepository
) {
    operator fun invoke(page: Int, language: String): Flow<Resource<List<TV>>> = flow{
        try {
            emit(Resource.loading(null))

            val data = tvSeriesRepository.getTopRatedTVs(page = page, language = language)

            emit(Resource.success(data.toTV()))

        }catch (e: Exception){
            emit(Resource.error("Connection or App Error", null))
        }
    }
}