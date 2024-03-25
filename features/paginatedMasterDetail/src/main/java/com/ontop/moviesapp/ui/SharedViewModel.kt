package com.ontop.moviesapp.ui

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.ontop.network.model.Movie

//  a generic SharedViewModel class to pass data between compsables
class SharedViewModel<T> : ViewModel() {
    private val _data = mutableStateOf<T?>(null)
    val data: State<T?> = _data

    fun setData(newData: T) {
        _data.value = newData
    }
}
