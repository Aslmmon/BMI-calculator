package com.ontop.inputapp.ui.result

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import androidx.annotation.Keep
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ontop.Variants
import com.ontop.data.model.BmiItem
import com.ontop.inputapp.ui.input.UserState
import com.ontop.repo.BmiRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch


@SuppressLint("DefaultLocale")
@HiltViewModel
class BMIResultViewModel : ViewModel() {

    private val _bmiState = MutableStateFlow<BMIState>(BMIState.Loading)
    val bmiState: StateFlow<BMIState> = _bmiState.asStateFlow()

    private var bmiRepository = BmiRepository()

    fun calculateBMi(userData: UserState.UserData, context: Context) {

        viewModelScope.launch {
            _bmiState.value = BMIState.Loading
            delay(1000L)
            try {
                val result = bmiRepository.getBmiResult(context)
                userData.height = getHeightInCm(userData)
                when (userData.heightVariantType) {
                    /**
                     * if weight in Pound will convert to KG then calculate BMI
                     */
                    Variants.Pounds -> userData.weight =
                        userData.weight?.let { poundsToKilograms(it.toDouble()) }?.toInt()

                    else -> {}
                }
                val heightInMeters = userData.height?.div(100.0) ?: 0.0
                val bmi = userData.weight!!.div((heightInMeters * heightInMeters))
                val bmiResult =
                    result.find { bmi in it.rangeFrom.toDouble()..it.rangeTo.toDouble() }
                val healthWeight = userData.height?.minus(100)

                _bmiState.value = BMIState.Success(
                    BMIResult(String.format("%.2f", bmi)),
                    BMIStatus(bmiResult),
                    HealthyWeight(healthWeight ?: 0)
                )
            } catch (e: Exception) {
                _bmiState.value = BMIState.Error("Error calculating BMI ${e.message}")
            }
        }
    }


    private fun inchesToCentimeters(inches: Double): Double {
        return inches * 2.54
    }


    private fun poundsToKilograms(pounds: Double): Double {
        return pounds * 0.453592
    }


    private fun getHeightInCm(userData: UserState.UserData): Int? {
        return when (userData.heightVariantType) {
            Variants.Inches -> userData.height?.let { inchesToCentimeters(it.toDouble()).toInt() }
            else -> userData.height
        }
    }


}

sealed class BMIState {
    @Keep
    data class Success(
        val bmiResult: BMIResult,
        val bmiStatus: BMIStatus,
        val healthyWeight: HealthyWeight
    ) : BMIState()

    object Loading : BMIState()

    @Keep
    data class Error(val message: String) : BMIState()
}

@Keep
data class BMIResult(val value: String = "0")

@Keep
data class HealthyWeight(val value: Int)

@Keep
data class BMIStatus(val bmiItem: BmiItem? = null)
