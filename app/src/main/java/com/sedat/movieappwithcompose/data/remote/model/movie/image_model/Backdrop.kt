package com.sedat.movieappwithcompose.data.remote.model.movie.image_model

import com.google.gson.annotations.SerializedName

data class Backdrop(
    @SerializedName("aspect_ratio")
    val aspectRatio: Float,
    @SerializedName("file_path")
    val filePath: String,
    val height: Int,
    @SerializedName("iso_639_1")
    val iso6391: Any,
    @SerializedName("vote_average")
    val voteAverage: Float,
    @SerializedName("vote_count")
    val voteCount: Int,
    val width: Int
){
    fun getImageUrl() = "https://image.tmdb.org/t/p/w500${filePath}"
}