package com.sedat.movieappwithcompose.data.remote.model

import com.google.gson.annotations.SerializedName
import com.sedat.movieappwithcompose.domain.model.MovieDetails

data class Result(
    val adult: Boolean,
    @SerializedName("backdrop_path")
    val backdropPath: String,
    @SerializedName("genre_ids")
    val genreIds: List<Int>,
    val id: Int,
    @SerializedName("original_language")
    val originalLanguage: String,
    @SerializedName("original_title")
    val originalTitle: String,
    @SerializedName("original_name")
    val originalName: String,
    val overview: String,
    val popularity: Float,
    @SerializedName("poster_path")
    val posterPath: String?,
    @SerializedName("release_date")
    val releaseDate: String,
    val title: String?,
    val video: Boolean,
    @SerializedName("vote_average")
    val voteAverage: Float,
    @SerializedName("vote_count")
    val voteCount: Int
){
    fun getImageUrl() = "https://image.tmdb.org/t/p/w500${posterPath}"
}

fun Result.toMovieDetails(): MovieDetails{
    return MovieDetails(
        overview = overview,
        posterPath = posterPath ?: "",
        releaseDate = releaseDate,
        title = title ?: originalName,
        voteAverage = voteAverage
    )
}