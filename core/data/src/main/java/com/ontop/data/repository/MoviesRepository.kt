package com.ontop.data.repository

import androidx.paging.PagingData
import com.ontop.network.model.Movie
import kotlinx.coroutines.flow.Flow

interface MoviesRepository {
    suspend fun getMovies(): Flow<PagingData<Movie>>
}