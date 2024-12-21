package com.ontop.inputapp.ui.input

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update


@HiltViewModel
class UpperCaseInputViewModel : ViewModel() {

    private val _startText = MutableStateFlow("")
    val startText: StateFlow<String> = _startText


    private val _resultText = MutableStateFlow("")
    val resultText: StateFlow<String> = _resultText


    fun updateStartText(textEnterd: String) =
        textEnterd.also {
            _startText.value = it
        }.also {
            generateUpperCasePhrase()
        }


    private fun generateUpperCasePhrase() {
        var isFirst = true
        _resultText.update {
            buildString {
                for (char in _startText.value) {
                    if (isFirst && char.isLetter()) {
                        append(char.uppercaseChar())
                        isFirst = false
                    } else {
                        append(char)
                        if (char.isWhitespace()) {
                            isFirst = true
                        }
                    }
                }
            }
        }
    }
}
