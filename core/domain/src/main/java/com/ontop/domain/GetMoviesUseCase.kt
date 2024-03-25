package com.ontop.domain

import androidx.paging.PagingData
import com.ontop.data.repository.MoviesRepository
import com.ontop.network.model.Movie
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * A use case which obtains a list of topics with their followed state.
 */

class GetMoviesUseCase @Inject constructor(
    private val moviesRepository: MoviesRepository,
) {
    /**
     * Returns a list of Movies with their associated followed state.
     *
     * */
    suspend operator fun invoke(): Flow<PagingData<Movie>> =
        moviesRepository.getMovies()

}

