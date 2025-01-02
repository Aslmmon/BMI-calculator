package com.ontop.inputapp.ui.input

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ontop.Variants
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch


@HiltViewModel
class UserInputViewModel : ViewModel() {

    private val _userData = MutableStateFlow(UserInputSelection.UserData())
    val userData: StateFlow<UserInputSelection.UserData> = _userData.asStateFlow()


    private val _loading = MutableStateFlow(UserInputSelection.loading(false))
    val loading: StateFlow<UserInputSelection.loading> = _loading.asStateFlow()

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

    fun updateHeightVariant(heightVariant: Variants) {
        _userData.value = _userData.value.copy(heightVariantType = heightVariant)
    }

    fun updateWeightVariant(weightVariant: Variants) {
        _userData.value = _userData.value.copy(weightVariantType = weightVariant)
    }

    fun showLoadingThenClick(onNextClick: () -> Unit) {
        _loading.value = _loading.value.copy(isLoading = true)
        viewModelScope.launch {
            delay(3000L)
            _loading.value = _loading.value.copy(isLoading = false)
            onNextClick.invoke()
        }

    }

}


sealed class UserInputSelection {
    data class UserData(
        var gender: Int? = 0,
        var age: Int? = null,
        var height: Int? = null,
        var weight: Int? = null,
        var heightVariantType: Variants = Variants.CM,
        var weightVariantType: Variants = Variants.KG
    )

    data class loading(var isLoading: Boolean)
}