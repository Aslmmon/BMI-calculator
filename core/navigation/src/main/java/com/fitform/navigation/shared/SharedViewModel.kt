package com.fitform.navigation.shared

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

//  a generic SharedViewModel class to pass data between compsables
class SharedViewModel<T> : ViewModel() {
    private val _data = mutableStateOf<T?>(null)
    val data: State<T?> = _data

    fun setData(newData: T) {
        _data.value = newData
    }
}
