package com.sedat.movieappwithcompose.data.remote.model.movie

import com.google.gson.annotations.SerializedName
import com.sedat.movieappwithcompose.domain.model.Movie

data class MovieDto(
    val dates: Dates?,
    val page: Int,
    val results: List<Result>,
    @SerializedName("total_pages")
    val totalPages: Int,
    @SerializedName("total_results")
    val totalResults: Int
)

fun MovieDto.toMovie(): List<Movie>{
    return results.map { result ->
        Movie(
            result.id,
            result.posterPath ?: "",
            result.title,
            result.releaseDate,
            result.voteAverage
        )
    }
}