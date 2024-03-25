package com.ontop.network

import com.ontop.network.model.MoviesResponse

interface MoviesDataSource {
    suspend fun getMovies(page: Int): MoviesResponse

}