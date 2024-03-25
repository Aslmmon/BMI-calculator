package com.ontop.data.di

import com.ontop.data.repository.MoviesRepository
import com.ontop.data.repository.MoviesRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DataModule {

    @Binds
    abstract fun bindsMoviesRepository(
        moviesRepository: MoviesRepositoryImpl,
    ): MoviesRepository


}