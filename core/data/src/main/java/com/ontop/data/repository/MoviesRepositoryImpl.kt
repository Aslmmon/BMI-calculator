package com.ontop.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.ontop.data.pagingDataSource.MoviePagingSource
import com.ontop.network.model.Movie
import com.ontop.network.retrofit.RetrofitMoviesNetworkApi
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

const val MAX_PAGE_SIZE = 1

class MoviesRepositoryImpl @Inject constructor(
    private val network: RetrofitMoviesNetworkApi,
) : MoviesRepository {
    override suspend fun getMovies(): Flow<PagingData<Movie>> {
        return Pager(
            config = PagingConfig(
                pageSize = MAX_PAGE_SIZE,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {
                MoviePagingSource(network)
            }
        ).flow
    }
}
