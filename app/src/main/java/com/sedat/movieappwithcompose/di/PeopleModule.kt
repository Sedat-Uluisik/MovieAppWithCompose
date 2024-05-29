package com.sedat.movieappwithcompose.di

import com.sedat.movieappwithcompose.data.remote.MovieAPI
import com.sedat.movieappwithcompose.data.repository.people.PeopleRepositoryImpl
import com.sedat.movieappwithcompose.domain.repository.people.PeopleRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object PeopleModule {
    @Provides
    @Singleton
    fun provideRepository(movieAPI: MovieAPI): PeopleRepository {
        return PeopleRepositoryImpl(movieAPI = movieAPI)
    }
}