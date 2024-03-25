package com.ontop.passwordgenerator.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import com.ontop.passwordgenerator.R
import kotlin.random.Random

@Composable
fun PasswordGeneratorScreen(
    modifier: Modifier = Modifier,
    passwordGeneratorViewModel: PasswordGeneratorViewModel = hiltViewModel()
) {
    val passwordLength by passwordGeneratorViewModel.passwordLength.collectAsState()
    val includeNumbers by passwordGeneratorViewModel.includeNumbers.collectAsState()
    val includeSymbols by passwordGeneratorViewModel.includeSymbols.collectAsState()
    val includeUppercase by passwordGeneratorViewModel.includeUppercase.collectAsState()
    val uiState by passwordGeneratorViewModel.uiState.collectAsState()


    Column(modifier = modifier.padding(16.dp)) {
        Row(modifier = modifier.padding(bottom = 8.dp)) {
            Text("${stringResource(id = R.string.password_length)}: $passwordLength")
            Spacer(modifier = modifier.width(8.dp))
        }

        Slider(
            value = passwordLength.toFloat() ?: 0f,
            onValueChange = {
                passwordGeneratorViewModel.updatePasswordLength(it.toInt())
            },
            valueRange = 5f..20f
        )

        CheckboxWithLabel(
            text = stringResource(R.string.include_uppercase_letters),
            checked = includeUppercase,
            onCheckedChange = {
                passwordGeneratorViewModel.updateUpperCase(it)
            }
        )

        CheckboxWithLabel(
            text = stringResource(R.string.include_numbers),
            checked = includeNumbers,
            onCheckedChange = {
                passwordGeneratorViewModel.updateIncludeNumbers(it)
            }
        )

        CheckboxWithLabel(
            text = stringResource(R.string.include_symbols),
            checked = includeSymbols,
            onCheckedChange = {
                passwordGeneratorViewModel.updateSymbols(it)
            }
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {
            passwordGeneratorViewModel.generatePassword()
        }) {
            Text(stringResource(R.string.generate_password))
        }

        Spacer(modifier = modifier.height(16.dp))
        when (uiState) {
            is UiState.passwordResultState -> {
                Text("${stringResource(R.string.generate_password)}: ${(uiState as UiState.passwordResultState).data}")
            }
        }
    }
}


@Composable
fun CheckboxWithLabel(
    text: String,
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit
) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Checkbox(
            checked = checked,
            onCheckedChange = onCheckedChange
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(text)
    }
}


