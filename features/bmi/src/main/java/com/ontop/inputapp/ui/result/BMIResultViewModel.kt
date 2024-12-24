package com.ontop.inputapp.ui.result

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import com.ontop.data.model.BmiItem
import com.ontop.inputapp.ui.input.UserInputSelection
import com.ontop.repo.BmiRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

@HiltViewModel
class BMIResultViewModel : ViewModel() {

    private val _bmiStatus = MutableStateFlow(BMiStatus.bmiStatus(null))
    val bmiStatus: StateFlow<BMiStatus.bmiStatus> = _bmiStatus.asStateFlow()

    private val _bmiResult = MutableStateFlow(BMiStatus.result("0"))
    val bmiResult: StateFlow<BMiStatus> = _bmiResult.asStateFlow()

    private var bmiRepository = BmiRepository()

    @SuppressLint("DefaultLocale")
    fun calculateBMi(userData: UserInputSelection.UserData, context: Context) {
        val result = bmiRepository.getBmiResult(context)

        val heightInMeters = userData.height?.div(100.0) ?: 0.0// Assuming height is in centimeters
        val bmi = userData.weight!!.div((heightInMeters * heightInMeters))
        val bmiResult = result.find { bmi in it.rangeFrom.toDouble()..it.rangeTo.toDouble() }
        _bmiResult.value = BMiStatus.result(String.format("%.2f", bmi))
        _bmiStatus.value = BMiStatus.bmiStatus(bmiResult)

    }
}


sealed class BMiStatus {
    data class result(val result: String = "0") : BMiStatus()
    data class bmiStatus(val bmiStatus: BmiItem? = null) : BMiStatus()

}