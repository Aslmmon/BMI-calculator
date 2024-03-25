package com.ontop.inputapp.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel


@Composable
fun BmiInputScreen(
    modifier: Modifier = Modifier,
    upperCaseInputViewModel: UpperCaseInputViewModel = hiltViewModel()
) {
//    val startText by upperCaseInputViewModel.startText.collectAsState()
//    val resultText by upperCaseInputViewModel.resultText.collectAsState()

    Column {
        Text(text = "welcome", modifier = Modifier.padding(16.dp))
    }
}
