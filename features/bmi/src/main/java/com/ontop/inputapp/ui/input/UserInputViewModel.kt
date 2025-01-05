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

    private val _userData = MutableStateFlow(UserState.UserData())
    val userData: StateFlow<UserState.UserData> = _userData.asStateFlow()

    private val _loading = MutableStateFlow(false)
    val loading: StateFlow<Boolean> = _loading.asStateFlow()


    fun updateUserData(
        age: Int? = null,
        gender: Int? = null,
        height: Int? = null,
        weight: Int? = null,
        heightVariant: Variants? = null,
        weightVariant: Variants? = null
    ) {
        _userData.value = _userData.value.copy(
            age = age ?: _userData.value.age,
            gender = gender ?: _userData.value.gender,
            height = height ?: _userData.value.height,
            weight = weight ?: _userData.value.weight,
            heightVariantType = heightVariant ?: _userData.value.heightVariantType,
            weightVariantType = weightVariant ?: _userData.value.weightVariantType
        )
    }


    fun showLoading() {
        _loading.value = true
        viewModelScope.launch {
            delay(2500L)
            stopLoading()
        }
    }

    private fun stopLoading() {
        _loading.value = false
    }
}


sealed class UserState {
    data class UserData(
        var gender: Int? = 0,
        var age: Int? = null,
        var height: Int? = null,
        var weight: Int? = null,
        var heightVariantType: Variants = Variants.CM,
        var weightVariantType: Variants = Variants.KG,
    )
}