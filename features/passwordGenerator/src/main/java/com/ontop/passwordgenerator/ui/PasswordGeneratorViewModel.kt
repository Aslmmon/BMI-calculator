package com.ontop.passwordgenerator.ui
import androidx.lifecycle.ViewModel
import com.ontop.passwordgenerator.lowercaseLetters
import com.ontop.passwordgenerator.numbers
import com.ontop.passwordgenerator.symbols
import com.ontop.passwordgenerator.uppercaseLetters
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

const val initialPasswordLength = 10
const val initialGeneratorValues = true

@HiltViewModel
class PasswordGeneratorViewModel : ViewModel() {
    private val _passwordLength = MutableStateFlow(initialPasswordLength)
    var passwordLength: StateFlow<Int> = _passwordLength


    private val _includeUppercase = MutableStateFlow(initialGeneratorValues)
    var includeUppercase: StateFlow<Boolean> = _includeUppercase

    private val _includeNumbers = MutableStateFlow(initialGeneratorValues)
    val includeNumbers: StateFlow<Boolean> = _includeNumbers


    private val _includeSymbols = MutableStateFlow(initialGeneratorValues)
    val includeSymbols: StateFlow<Boolean> = _includeSymbols

    private val _uiState = MutableStateFlow<UiState>(UiState.passwordResultState(""))
    val uiState: StateFlow<UiState> = _uiState


    fun updateSymbols(isSymbolsIncluded: Boolean) =
        isSymbolsIncluded.also { _includeSymbols.value = it }

    fun updateUpperCase(isUpperCaseIncluded: Boolean) =
        isUpperCaseIncluded.also { _includeUppercase.value = it }


    fun updateIncludeNumbers(isNumbersSelected: Boolean) =
        isNumbersSelected.also { _includeNumbers.value = it }

    fun updatePasswordLength(passwordLength: Int) =
        passwordLength.also { _passwordLength.value = it }

    fun generatePassword(
    ) {
        val uppercaseLetters = if (_includeUppercase.value) uppercaseLetters else emptyList()
        val numbers = if (_includeNumbers.value) numbers else emptyList()
        val symbols = if (_includeSymbols.value) symbols else emptyList()

        val allCharacters = lowercaseLetters + uppercaseLetters + numbers + symbols
        _uiState.update {
            UiState.passwordResultState(buildString {
                repeat(_passwordLength.value) {
                    append(allCharacters.random())
                }
            })
        }
    }
}

sealed class UiState {
    data class passwordResultState(val data: String) : UiState()
}
