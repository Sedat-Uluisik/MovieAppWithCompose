package com.sedat.movieappwithcompose.domain.model

data class MovieDetails(
    val overview: String,
    val posterPath: String,
    val releaseDate: String,
    val title: String,
    val voteAverage: Float
)

fun MovieDetails.getMoviePosterUrl(): String{
    return "https://image.tmdb.org/t/p/w500${posterPath}"
}
