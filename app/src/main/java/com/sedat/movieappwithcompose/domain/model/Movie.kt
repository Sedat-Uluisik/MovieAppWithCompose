package com.sedat.movieappwithcompose.domain.model

data class Movie(
    val id: Int,
    val posterPath: String,
    val title: String,
    val releaseDate: String,
    val voteAverage: Float,
)

fun Movie.getMoviePosterUrl(): String{
    return "https://image.tmdb.org/t/p/w500${posterPath}"
}
