package com.sedat.movieappwithcompose.domain.model

data class TV(
    val id: Int,
    val posterPath: String,
    val title: String,
    val firstAirDate: String,
    val voteAverage: Float
)

fun TV.getTVPosterUrl(): String{
    return "https://image.tmdb.org/t/p/w500${posterPath}"
}
