package com.sedat.movieappwithcompose.data.remote.model.people

import com.google.gson.annotations.SerializedName
import com.sedat.movieappwithcompose.data.remote.model.Result

data class PeopleResult(
    val adult: Boolean,
    val gender: Int,
    @SerializedName("known_for_department")
    val knownForDepartment: String,
    val name: String,
    @SerializedName("original_name")
    val originalName: String,
    val popularity: Float,
    @SerializedName("profile_path")
    val profilePath: String,
    @SerializedName("known_for")
    val knownFor: List<Result>
)

fun PeopleResult.getPosterUrl() = "https://image.tmdb.org/t/p/w500${profilePath}"
