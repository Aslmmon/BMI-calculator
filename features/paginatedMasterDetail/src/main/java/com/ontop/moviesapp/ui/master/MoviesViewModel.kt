package com.ontop.moviesapp.ui.master

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.filter
import com.ontop.domain.GetMoviesUseCase
import com.ontop.network.model.Movie
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class MoviesViewModel @Inject constructor(
    private val getMoviesUseCase: GetMoviesUseCase
) : ViewModel() {

    private var _moviesState: MutableStateFlow<PagingData<Movie>> =
        MutableStateFlow(PagingData.empty())
    val moviesState: MutableStateFlow<PagingData<Movie>> get() = _moviesState


    init {
        getMovies()
    }

    private fun getMovies() {
        viewModelScope.launch {
            getMoviesUseCase.invoke()
                .distinctUntilChanged()
                .cachedIn(viewModelScope)
                .collect { pagingData ->
                    moviesState.value = pagingData
                }
        }
    }

    fun searchItems(items: Flow<PagingData<Movie>>, query: String): Flow<PagingData<Movie>> {
        return items.map { pagingData ->
            pagingData.filter {
                it.title?.contains(query.removeEmptySpaces(), ignoreCase = true) ?: false
            }
        }
    }
}

fun String.removeEmptySpaces() = this.filterNot { it.isWhitespace() }

