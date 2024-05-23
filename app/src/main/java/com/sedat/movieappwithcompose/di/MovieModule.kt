package com.sedat.movieappwithcompose.di

import com.sedat.movieappwithcompose.data.remote.MovieAPI
import com.sedat.movieappwithcompose.data.repository.movie.MovieRepositoryImpl
import com.sedat.movieappwithcompose.domain.repository.movie.MovieRepository
import com.sedat.movieappwithcompose.util.Constants.Companion.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MovieModule {
    @Provides
    @Singleton
    fun provideRetrofit(): MovieAPI{
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(MovieAPI::class.java)
    }

    @Provides
    @Singleton
    fun provideRepository(movieAPI: MovieAPI): MovieRepository{
        return MovieRepositoryImpl(movieAPI = movieAPI)
    }
}