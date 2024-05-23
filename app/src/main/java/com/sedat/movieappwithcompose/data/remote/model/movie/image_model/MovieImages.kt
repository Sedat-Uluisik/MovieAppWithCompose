package com.sedat.movieappwithcompose.data.remote.model.movie.image_model

data class MovieImages(
    val backdrops: List<Backdrop>,
    val id: Int,
    val logos: List<Logo>,
    val posters: List<Poster>
)