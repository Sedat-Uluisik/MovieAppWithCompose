package com.sedat.movieappwithcompose.di

import com.sedat.movieappwithcompose.data.remote.MovieAPI
import com.sedat.movieappwithcompose.data.repository.people.PersonRepositoryImpl
import com.sedat.movieappwithcompose.data.repository.tv_series.TVRepositoryImpl
import com.sedat.movieappwithcompose.domain.repository.people.PersonRepository
import com.sedat.movieappwithcompose.domain.repository.tv_series.TvSeriesRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object TVModule {
    @Provides
    @Singleton
    fun provideRepository(movieAPI: MovieAPI): TvSeriesRepository {
        return TVRepositoryImpl(movieAPI = movieAPI)
    }
}