package com.ontop.inputapp.ui.input

import android.util.Log
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow


@HiltViewModel
class UserInputViewModel : ViewModel() {

    private val _userInputSelection =
        MutableStateFlow<UserInputSelection>(UserInputSelection.Gender(-1))
    val userInputSelection: StateFlow<UserInputSelection> = _userInputSelection.asStateFlow()


    fun updateAge(age: Int) {

        _userInputSelection.value = UserInputSelection.Age(age)
        Log.d("age", "age chosen is ${userInputSelection.value}")

    }

    fun updateGender(gender: Int) {
        _userInputSelection.value = UserInputSelection.Gender(gender)
        Log.d("gender", "gender chosen is ${userInputSelection.value}")

    }

    fun updateHeight(height: Int) {
        _userInputSelection.value = UserInputSelection.Height(height)
        Log.d("Height", "Height chosen is ${userInputSelection.value}")

    }

    fun updateWeight(weight: Int) {
        _userInputSelection.value = UserInputSelection.Weight(weight)
        Log.d("Weight", "Weight chosen is ${userInputSelection.value}")

    }


}


sealed class UserInputSelection {
    data class Gender(val value: Int) : UserInputSelection()
    data class Age(val value: Int) : UserInputSelection()
    data class Height(val value: Int) : UserInputSelection()
    data class Weight(val value: Int) : UserInputSelection()
}