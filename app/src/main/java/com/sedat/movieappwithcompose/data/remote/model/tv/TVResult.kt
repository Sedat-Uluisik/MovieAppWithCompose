package com.sedat.movieappwithcompose.data.remote.model.tv

import com.google.gson.annotations.SerializedName

data class TVResult(
    val adult: Boolean,
    @SerializedName("backdrop_path")
    val backdropPath: String,
    @SerializedName("genre_ids")
    val genreIds: List<Int>,
    val id: Int,
    @SerializedName("origin_country")
    val originCountry: List<String>,
    @SerializedName("original_language")
    val originalLanguage: String,
    @SerializedName("original_name")
    val originalName: String?,
    val overview: String,
    val popularity: Float,
    @SerializedName("poster_path")
    val posterPath: String?,
    @SerializedName("first_air_date")
    val firstAirDate: String?,
    val name: String?,
    @SerializedName("vote_average")
    val voteAverage: Float,
    @SerializedName("vote_count")
    val voteCount: Int
)