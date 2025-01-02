package com.ontop.inputapp.ui.result

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import com.ontop.Variants
import com.ontop.data.model.BmiItem
import com.ontop.inputapp.ui.input.UserInputSelection
import com.ontop.repo.BmiRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow


@SuppressLint("DefaultLocale")
@HiltViewModel
class BMIResultViewModel : ViewModel() {

    private val _bmiStatus = MutableStateFlow(BMiStatus.bmiStatus(null))
    val bmiStatus: StateFlow<BMiStatus.bmiStatus> = _bmiStatus.asStateFlow()

    private val _bmiResult = MutableStateFlow(BMiStatus.result("0"))
    val bmiResult: StateFlow<BMiStatus.result> = _bmiResult.asStateFlow()

    private var bmiRepository = BmiRepository()

    fun calculateBMi(userData: UserInputSelection.UserData, context: Context) {
        val result = bmiRepository.getBmiResult(context)
        when (userData.heightVariantType) {
            /**
             * if height in inches will convert to CM then calculate BMI
             */
            Variants.Inches -> userData.height =
                userData.height?.let { inchesToCentimeters(it.toDouble()) }?.toInt()

            /**
             * if weight in Pound will convert to KG then calculate BMI
             */
            Variants.Pounds -> userData.weight =
                userData.weight?.let { poundsToKilograms(it.toDouble()) }?.toInt()

            else -> {}
        }
        Log.d("BMIResultViewModel", "data Shared: ${userData.toString()}")

        val heightInMeters = userData.height?.div(100.0) ?: 0.0
        val bmi = userData.weight!!.div((heightInMeters * heightInMeters))
        val bmiResult = result.find { bmi in it.rangeFrom.toDouble()..it.rangeTo.toDouble() }
        _bmiResult.value = BMiStatus.result(String.format("%.2f", bmi))
        _bmiStatus.value = BMiStatus.bmiStatus(bmiResult)

    }

    private fun inchesToCentimeters(inches: Double): Double {
        val centimeters = inches * 2.54
        Log.d("BMIResultViewModel", "new height : $centimeters")

        return String.format("%.2f", centimeters).toDouble() // Round to 2 decimal places
    }

    private fun poundsToKilograms(pounds: Double): Double {
        val kilograms = pounds * 0.453592
        Log.d("BMIResultViewModel", "new weight : $kilograms")
        return String.format("%.2f", kilograms).toDouble() // Round to 2 decimal places
    }

}


sealed class BMiStatus {
    data class result(val value: String = "0") : BMiStatus()
    data class bmiStatus(val bmiStatus: BmiItem? = null) : BMiStatus()

}