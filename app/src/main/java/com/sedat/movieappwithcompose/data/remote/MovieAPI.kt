package com.sedat.movieappwithcompose.data.remote

import com.sedat.movieappwithcompose.data.remote.model.LanguageItem
import com.sedat.movieappwithcompose.data.remote.model.movie.MovieDto
import com.sedat.movieappwithcompose.data.remote.model.movie.Result
import com.sedat.movieappwithcompose.data.remote.model.movie.image_model.MovieImages
import com.sedat.movieappwithcompose.util.Constants.Companion.API_KEY
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieAPI {

    @GET("/3/movie/popular")
    suspend fun getMovies(
        @Query("page") page: Int,
        @Query("language") language: String,
        @Query("api_key") apiKey: String = API_KEY
    ): MovieDto

    @GET("/3/configuration/languages")
    suspend fun getLanguages(
        @Query("api_key") apiKey: String = API_KEY
    ): Response<List<LanguageItem>>

    @GET("/3/search/movie")
    suspend fun searchMovie(
        @Query("query") query: String,
        @Query("language") language: String,
        @Query("api_key") apiKey: String = API_KEY
    ): Response<MovieDto>

    @GET("/3/trending/movie/{time_window}")
    suspend fun getTrendMovies(
        @Path("time_window") time: String,
        @Query("language") language: String,
        @Query("region") region: String,
        @Query("page") page: Int,
        @Query("api_key") apiKey: String = API_KEY
    ): Response<MovieDto>

    @GET("/3/movie/{movie_id}?")
    suspend fun getMovie(
        @Path("movie_id") movieId: Int,
        @Query("language") language: String,
        @Query("api_key") apiKey: String = API_KEY
    ): Response<Result>

    @GET("/3/movie/{movie_id}/images")
    suspend fun getMovieImages(
        @Path("movie_id") movieId: Int,
        @Query("api_key") apiKey: String = API_KEY
    ): Response<MovieImages>

    //filme ait fragmanları getirir
    //https://api.themoviedb.org/3/movie/{movie_id}/videos?language=tr-TR&api_key=893869b08fc400b476a6aa26e183685a

    //en çok oy alanlar filmler
    //https://api.themoviedb.org/3/movie/top_rated?language=en-US&page=1&api_key=893869b08fc400b476a6aa26e183685a

    //yaklaşan filmler
    //https://api.themoviedb.org/3/movie/upcoming?language=tr-TR&page=1&region=TR

    //search movie-tv shows-people multi arama
    //https://api.themoviedb.org/3/search/multi?query=car&include_adult=true&language=tr&page=1&api_key=893869b08fc400b476a6aa26e183685a

    //----------------------------------------------  trendler sayfası için

    //trend filmler
    //https://api.themoviedb.org/3/trending/movie/(week/day)?language=tr

    //trend people
    //https://api.themoviedb.org/3/trending/person/(day/week))?language=en-US

    //trend tv shows
    //https://api.themoviedb.org/3/trending/tv/{day or week}?language=en-US

    //----------------------  tv ile ilgililer

    //popüler tv serileri
    //https://api.themoviedb.org/3/tv/popular?language=tr&page=1

    //en çok oy alan tv serileri
    //https://api.themoviedb.org/3/tv/top_rated?language=en-US&page=1

    //tv detayı
    //https://api.themoviedb.org/3/tv/94954?language=en-US

    //tv fragmanı
    //https://api.themoviedb.org/3/tv/94954/videos?include_video_language=tr&language=tr

    //-------------------------------- kişiler

    //popüler kişiler
    //https://api.themoviedb.org/3/person/popular?language=tr&page=1
}