package com.sedat.movieappwithcompose.data.remote.model.tv

import com.google.gson.annotations.SerializedName
import com.sedat.movieappwithcompose.domain.model.TV

data class TVDto(
    val page: Int,
    val results: List<TVResult>,
    @SerializedName("total_pages")
    val totalPages: Int,
    @SerializedName("total_results")
    val totalResults: Int
)

fun TVDto.toTV(): List<TV>{
    return results.map { tvResult ->
        TV(
            tvResult.id,
            tvResult.posterPath ?: "",
            tvResult.originalName ?: "",
            tvResult.firstAirDate ?: "",
            tvResult.voteAverage
        )
    }
}
