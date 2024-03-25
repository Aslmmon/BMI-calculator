package com.ontop.data.pagingDataSource

import androidx.paging.PagingSource
import androidx.paging.PagingSource.LoadResult.Error
import androidx.paging.PagingState
import com.ontop.network.model.Movie
import com.ontop.network.retrofit.RetrofitMoviesNetworkApi

const val PAGE_NUMBER = 1

class MoviePagingSource(
    private val network: RetrofitMoviesNetworkApi,
) : PagingSource<Int, Movie>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {
        return try {
            val currentPage = params.key ?: PAGE_NUMBER
            val movieResponse = network.getMovies(
                page = currentPage
            )
            LoadResult.Page(
                data = movieResponse.movies,
                prevKey = if (currentPage == PAGE_NUMBER) null else currentPage - PAGE_NUMBER,
                nextKey = if (movieResponse.movies.isEmpty()) null else movieResponse.page + PAGE_NUMBER
            )
        } catch (exception: Exception) {
            return Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Movie>): Int? {
        return state.anchorPosition
    }

}