package com.sedat.movieappwithcompose.data.remote.model.people

import com.google.gson.annotations.SerializedName

data class PeopleDto(
    val page: Int,
    val results: List<PeopleResult>,
    @SerializedName("total_pages")
    val totalPages: Int,
    @SerializedName("total_results")
    val totalResults: Int
)
