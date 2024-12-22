package com.ontop.inputapp.ui.input

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow


@HiltViewModel
class UserInputViewModel : ViewModel() {

    private val _userData = MutableStateFlow(UserInputSelection.UserData(0, 0, 0, 0))
    val userData: StateFlow<UserInputSelection.UserData> = _userData.asStateFlow()

    fun updateAge(age: Int) {
        _userData.value = _userData.value.copy(age = age)
    }

    fun updateGender(gender: Int) {
        _userData.value = _userData.value.copy(gender = gender)
    }

    fun updateHeight(height: Int) {
        _userData.value = _userData.value.copy(height = height)
    }

    fun updateWeight(weight: Int) {
        _userData.value = _userData.value.copy(weight = weight)
    }


}


sealed class UserInputSelection {
    data class UserData(var gender: Int, var age: Int, var height: Int, var weight: Int)
}